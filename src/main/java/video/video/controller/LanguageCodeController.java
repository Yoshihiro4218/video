package video.video.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import video.video.service.LanguageCodeService;

@Controller
@RequestMapping("/settings/codes")
public class LanguageCodeController {
    private final LanguageCodeService languageCodeService;

    public LanguageCodeController(LanguageCodeService languageCodeService) {
        this.languageCodeService = languageCodeService;
    }

//    @GetMapping("")
//    public String codeIndex (Model model) {
//        List<CodeDto> codeList = codeService
//        return "setting/code/index";
//    }
}
