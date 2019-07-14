package video.video.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import video.video.dto.LanguageCodeDto;
import video.video.service.LanguageCodeService;

import java.util.List;

@Controller
@RequestMapping("/settings/codes/languages")
public class LanguageCodeController {
    private final LanguageCodeService languageCodeService;

    public LanguageCodeController(LanguageCodeService languageCodeService) {
        this.languageCodeService = languageCodeService;
    }

    @GetMapping("")
    public String languageIndex (Model model) {
        List<LanguageCodeDto> languageList = languageCodeService.selectLanguageCodeList();
        model.addAttribute("languages", languageList);
        return "setting/code/language/index";
    }
}
