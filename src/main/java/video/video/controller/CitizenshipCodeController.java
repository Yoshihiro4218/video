package video.video.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import video.video.service.CitizenshipCodeService;

@Controller
@RequestMapping("/settings/codes")
public class CitizenshipCodeController {
    private final CitizenshipCodeService citizenshipCodeService;

    public CitizenshipCodeController(CitizenshipCodeService citizenshipCodeService) {
        this.citizenshipCodeService = citizenshipCodeService;
    }

//    @GetMapping("")
//    public String codeIndex (Model model) {
//        List<CodeDto> codeList = codeService
//        return "setting/code/index";
//    }
}
