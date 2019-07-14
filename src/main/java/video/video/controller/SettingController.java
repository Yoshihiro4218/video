package video.video.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings")
public class SettingController {
    @GetMapping("")
    public String settingIndex () {
        return "setting/index";
    }

    @GetMapping("/codes")
    public String codeIndex () {
        return "setting/code/index";
    }
}
