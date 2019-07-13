package video.video.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import video.video.dto.CodeDto;
import video.video.dto.VideoDto;
import video.video.service.CodeService;
import video.video.service.MovieService;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final CodeService codeService;

    public MovieController(MovieService movieService, CodeService codeService){
        this.movieService = movieService;
        this.codeService = codeService;
    }

    @GetMapping("")
    public String getIndexList(Model model) {
        List<VideoDto> list = movieService.indexMovieList();
        model.addAttribute("movieList", list);
        return "movie/index";
    }

    @GetMapping("/{id}")
    public String getMovieDetail(@PathVariable("id") int movieId, Model model) {
        VideoDto movie = movieService.showMovieDetail(movieId);
        List actor = movieService.showActorDetailWithMovie(movieId);
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
        List<CodeDto> languageList = codeService.selectSpecificCodeList(1);
        model.addAttribute("languageList", languageList);
        return "movie/new";
    }
}
