package video.video.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import video.video.dto.CodeDto;
import video.video.service.CodeService;

import java.util.List;

@Controller
@RequestMapping("/settings/codes")
public class CodeController {
    private final CodeService codeService;

    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

//    @GetMapping("")
//    public String codeIndex (Model model) {
//        List<CodeDto> codeList = codeService
//        return "setting/code/index";
//    }
}
