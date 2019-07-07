package video.video.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import video.video.dto.MovieDto;
import video.video.service.MovieService;

import java.util.List;

@Controller
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping("")
    public String getIndexList(Model model) {
        List<MovieDto> list = movieService.indexMovieList();
        model.addAttribute("movieList", list);
        return "movie/index";
    }
}
