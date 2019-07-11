package video.video.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import video.video.dto.VideoDto;
import video.video.service.MovieService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService){
        this.movieService = movieService;
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
}
