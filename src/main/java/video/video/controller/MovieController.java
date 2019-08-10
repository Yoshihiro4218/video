package video.video.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import video.video.dto.LanguageCodeDto;
import video.video.dto.MovieDto;
import video.video.service.CitizenshipCodeService;
import video.video.service.LanguageCodeService;
import video.video.service.MovieService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/movies")
@Slf4j
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
    public String getIndexList(@RequestParam(value = "search", required = false) String search,
                               @RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "isWatched", required = false) Boolean isWatched,
                               @RequestParam(value = "year", required = false) Integer year,
                               Model model) {
        page = Optional.ofNullable(page).orElse(1);
        search = Optional.ofNullable(search).orElse("%");
        Optional<Integer> maybeYear = Optional.ofNullable(year);
        List<MovieDto> list = movieService.indexMovieList(search, page, isWatched, maybeYear, model);
        model.addAttribute("movieList", list);
        model.addAttribute("isWatched", isWatched);
        model.addAttribute("previousPage", page - 1);
        model.addAttribute("currentPage", page);
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
        model.addAttribute("movieDto", new MovieDto());
        return "movie/new";
    }

    @PostMapping("/new")
    public String postNewMovie(@Validated @ModelAttribute MovieDto movieDto, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            List<LanguageCodeDto> languageList = languageCodeService.selectLanguageCodeList();
            model.addAttribute("languageList", languageList);
            model.addAttribute("movieDto", movieDto);
            return "movie/new";
        }
        int movieId = movieService.createNewMovie(movieDto);
        return "redirect:/movies/" + movieId;
    }

    @PostMapping("/{id}/delete")
    public String deleteMovie(@PathVariable("id") int movieId, RedirectAttributes attributes) {
        int res = movieService.deleteMovie(movieId);
        attributes.addAttribute("deleteMovieId", movieId);
        attributes.addAttribute("deleteOk", res);
        return "redirect:/movies";
    }
}
