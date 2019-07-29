package video.video.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;
import video.video.dto.MovieDto;

import java.util.List;

@Service
@Slf4j
@CrossOrigin
public class MovieService {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public RestTemplate restTemplate;
    private static final RowMapper<MovieDto> MAPPER = new BeanPropertyRowMapper<>(MovieDto.class);

    public MovieService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<MovieDto> indexMovieList(int page, Model model) {
            int movieSumNum = 15;
            List<MovieDto> list = jdbcTemplate.query("SELECT id, title, release_year, watched_flg FROM movie ORDER BY id;", MAPPER);
            log.info("MovieList:{}", list);
            int maxPage = (int)Math.ceil((double)list.size() / (double)movieSumNum);
            model.addAttribute("maxPage", maxPage);
            if(page + 1 > maxPage) {
                model.addAttribute("nextPage", 0);
            }else {
                model.addAttribute("nextPage", page + 1);
            }
            if(list.size() < page * movieSumNum) {
                list = list.subList((page - 1) * movieSumNum, list.size());
            }else{
                list = list.subList((page - 1) * movieSumNum, page * movieSumNum);
            }
        return list;
    }

    public MovieDto showMovieDetail(int movieId) {
        String movieSql = "SELECT * FROM movie WHERE id = ?;";
        MovieDto movie = jdbcTemplate.queryForObject(movieSql, MAPPER, movieId);
        log.info("Movie:{}", movie);
        return movie;
    }

    public List showActorDetailWithMovie(MovieDto movie) {
        String actorSql;
        List actorList = null;
        if(movie.getStarringNum4()!=null){
            actorSql = "SELECT actor.name FROM movie JOIN actor ON " +
                    "movie.starring_num1 = actor.id " +
                    "OR movie.starring_num2 = actor.id " +
                    "OR movie.starring_num3 = actor.id " +
                    "OR movie.starring_num4 = actor.id " +
                    "WHERE movie.id = ?;";
            actorList = jdbcTemplate.queryForList(actorSql, movie.getId());
        } else if(movie.getStarringNum3()!=null) {
            actorSql = "SELECT actor.name FROM movie JOIN actor ON " +
                    "movie.starring_num1 = actor.id " +
                    "OR movie.starring_num2 = actor.id " +
                    "OR movie.starring_num3 = actor.id " +
                    "WHERE movie.id = ?;";
            actorList = jdbcTemplate.queryForList(actorSql, movie.getId());
            actorList.add(3, null);
        } else if (movie.getStarringNum2()!=null) {
            actorSql = "SELECT actor.name FROM movie JOIN actor ON " +
                    "movie.starring_num1 = actor.id " +
                    "OR movie.starring_num2 = actor.id " +
                    "WHERE movie.id = ?;";
            actorList = jdbcTemplate.queryForList(actorSql, movie.getId());
            actorList.add(2, null);
            actorList.add(3, null);
        } else if (movie.getStarringNum1()!=null) {
            actorSql = "SELECT actor.name FROM movie JOIN actor ON " +
                    "movie.starring_num1 = actor.id " +
                    "WHERE movie.id = ?;";
            actorList = jdbcTemplate.queryForList(actorSql, movie.getId());
            actorList.add(1, null);
            actorList.add(2, null);
            actorList.add(3, null);
        } else {
            actorList.add(0, null);
            actorList.add(1, null);
            actorList.add(2, null);
            actorList.add(3, null);
        }
        log.info("ActorWithMovie:{}", actorList);
        return actorList;
    }

    public String createMovieImageUrl(String movieOriginalTitle, int releaseYear) {
        String searchBaseUrl = "https://api.themoviedb.org/3/search/movie?api_key=";
        String apiKey = "55ad46a72208ad85dccabde0716a7652";
        String imageBaseUrl = "https://image.tmdb.org/t/p/w500/";
        String spaceReplacedMovieOriginalTitle = movieOriginalTitle.replace(" ", "%20");
        String year = String.valueOf(releaseYear);
        String searchUrl = searchBaseUrl + apiKey + "&query=" + spaceReplacedMovieOriginalTitle + "&page=1&year=" + year;
        String res = restTemplate.getForObject(searchUrl, String.class);
        JSONObject json = new JSONObject(res);
        String posterPath;
        if(json.getInt("total_results")==(0)) {
            posterPath = "/image/noImage.png";
            imageBaseUrl = "";
        } else {
            posterPath = json.getJSONArray("results").getJSONObject(0).getString("poster_path");
        }
        return imageBaseUrl + posterPath;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public String updateWatchedFlg(int movieId) {
        MovieDto movie = jdbcTemplate.queryForObject("SELECT * FROM movie WHERE id = ?;", MAPPER, movieId);
        Boolean nowWatchedFlg = movie.isWatchedFlg();
        Boolean updatedWatchedFlg;
        if(nowWatchedFlg==true) {
            updatedWatchedFlg = false;
        } else {
            updatedWatchedFlg = true;
        }
        jdbcTemplate.update("UPDATE movie SET watched_flg = ? WHERE id = ?;", updatedWatchedFlg, movieId);
        log.info("UpdatedFlg:{}", nowWatchedFlg);
        return String.valueOf(updatedWatchedFlg);
    }

    public int createNewMovie(MovieDto movieDto) {
        String updateSql = "INSERT INTO movie(title, search_title, release_year, show_times, original_language, " +
                     "starring_num1, starring_num2, starring_num3, starring_num4, watched_flg) " +
                     "VALUE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(updateSql, movieDto.getTitle(), movieDto.getSearchTitle(), movieDto.getReleaseYear(), movieDto.getShowTimes(),
                movieDto.getOriginalLanguage(), movieDto.getStarringNum1(), movieDto.getStarringNum2(), movieDto.getStarringNum3(),
                movieDto.getStarringNum4(), movieDto.isWatchedFlg());

        String selectMovieIdSql = "SELECT MAX(id) FROM movie;";
        int movieId = jdbcTemplate.queryForObject(selectMovieIdSql, int.class);
        return movieId;
    }
}
