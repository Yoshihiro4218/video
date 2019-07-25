package video.video.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import video.video.dto.LanguageCodeDto;
import video.video.dto.MovieDto;
import video.video.service.CitizenshipCodeService;
import video.video.service.LanguageCodeService;
import video.video.service.MovieService;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final LanguageCodeService languageCodeService;
    private final CitizenshipCodeService citizenshipCodeService;

    public MovieController(MovieService movieService, LanguageCodeService languageCodeService, CitizenshipCodeService citizenshipCodeService){
        this.movieService = movieService;
        this.languageCodeService = languageCodeService;
        this.citizenshipCodeService = citizenshipCodeService;
    }

    @GetMapping("")
    public String getIndexList(Model model) {
        List<MovieDto> list = movieService.indexMovieList();
        model.addAttribute("movieList", list);
        return "movie/index";
    }

    @GetMapping("/{id}")
    public String getMovieDetail(@PathVariable("id") int movieId, Model model) {
        MovieDto movie = movieService.showMovieDetail(movieId);
        List actor = movieService.showActorDetailWithMovie(movie);
        String imageUrl = movieService.createMovieImageUrl(movie.getSearchTitle(), movie.getReleaseYear());
        model.addAttribute("movieDetail", movie);
        model.addAttribute("actorName1", actor.get(0));
        model.addAttribute("actorName2", actor.get(1));
        model.addAttribute("actorName3", actor.get(2));
        model.addAttribute("actorName4", actor.get(3));
        model.addAttribute("imageUrl", imageUrl);
        return "movie/show";
    }

    @PostMapping("/{id}/watched")
    @ResponseBody
    public String watchedBtnClicked(@PathVariable("id") int movieId) {
        String updatedWatchedFlg = movieService.updateWatchedFlg(movieId);
        return updatedWatchedFlg;
    }

    @GetMapping("/new")
    public String moveNewMovieForm(Model model) {
        List<LanguageCodeDto> languageList = languageCodeService.selectLanguageCodeList();
        model.addAttribute("languageList", languageList);
        return "movie/new";
    }

    @PostMapping("/new")
    public String postNewMovie(@ModelAttribute MovieDto movieDto) {
        int movieId = movieService.createNewMovie(movieDto);
        return "redirect:/movies/" + movieId;
    }
}
