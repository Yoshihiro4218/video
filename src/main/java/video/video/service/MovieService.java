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
import org.springframework.web.client.RestTemplate;
import video.video.dto.VideoDto;

import java.util.List;

@Service
@Slf4j
public class MovieService {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public RestTemplate restTemplate;
    private static final RowMapper<VideoDto> MAPPER = new BeanPropertyRowMapper<>(VideoDto.class);

    public MovieService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<VideoDto> indexMovieList() {
        List<VideoDto> list = jdbcTemplate.query("SELECT id, title, release_year, watched_flg FROM movie ORDER BY id", MAPPER);
        log.info("MovieList:{}", list);
        return list;
    }

    public VideoDto showMovieDetail(int movieId) {
        String movieSql = "SELECT * FROM movie WHERE id = ?";
        VideoDto movie = jdbcTemplate.queryForObject(movieSql, MAPPER, movieId);
        log.info("Movie:{}", movie);
        return movie;
    }

    public List showActorDetailWithMovie(int movieId) {
        String actorSql = "SELECT actor.name FROM movie JOIN actor ON " +
                "movie.starring_num1 = actor.id " +
                "OR movie.starring_num2 = actor.id " +
                "OR movie.starring_num3 = actor.id " +
                "OR movie.starring_num4 = actor.id " +
                "WHERE movie.id = ?";
        List actorList = jdbcTemplate.queryForList(actorSql, movieId);
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
        log.info("searchUrl={}", searchUrl);
        String res = restTemplate.getForObject(searchUrl, String.class);
        log.info("res={}", res);
        JSONObject json = new JSONObject(res);
        String posterPath = json.getJSONArray("results").getJSONObject(0).getString("poster_path");

        return imageBaseUrl + posterPath;

    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
