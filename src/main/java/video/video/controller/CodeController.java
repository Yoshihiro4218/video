package video.video.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings/codes")
public class CodeController {

    @GetMapping("")
    public String codeIndex () {
        return "setting/code/index";
    }
}
