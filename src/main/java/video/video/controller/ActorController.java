package video.video.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import video.video.dto.ActorDto;
import video.video.dto.MovieDto;
import video.video.service.ActorService;
import video.video.service.CitizenshipCodeService;
import video.video.service.LanguageCodeService;

import java.util.List;

@Controller
@RequestMapping("/actors")
public class ActorController {

    private final ActorService actorService;
    private final LanguageCodeService languageCodeService;
    private final CitizenshipCodeService citizenshipCodeService;

    public ActorController(ActorService actorService, LanguageCodeService languageCodeService, CitizenshipCodeService citizenshipCodeService){
        this.actorService = actorService;
        this.languageCodeService = languageCodeService;
        this.citizenshipCodeService = citizenshipCodeService;
    }

    @GetMapping("")
    public String getIndexList(Model model) {
        List<ActorDto> list = actorService.indexActorList();
        model.addAttribute("actorList", list);
        return "actor/index";
    }

    @GetMapping("/{id}")
    public String getActorDetail(@PathVariable("id") int actorId, Model model) {
        ActorDto actor = actorService.showActorDetail(actorId);
        model.addAttribute("actorDetail", actor);
        return "actor/show";
    }
}
