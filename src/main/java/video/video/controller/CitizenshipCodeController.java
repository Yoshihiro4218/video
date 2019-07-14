package video.video.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import video.video.dto.CitizenshipCodeDto;
import video.video.service.CitizenshipCodeService;

import java.util.List;

@Controller
@RequestMapping("/settings/codes/citizenships")
public class CitizenshipCodeController {
    private final CitizenshipCodeService citizenshipCodeService;

    public CitizenshipCodeController(CitizenshipCodeService citizenshipCodeService) {
        this.citizenshipCodeService = citizenshipCodeService;
    }

    @GetMapping("")
    public String citizenshipList (Model model) {
        List<CitizenshipCodeDto> citizenshipList = citizenshipCodeService.selectCitizenshipCodeList();
        model.addAttribute("citizenships", citizenshipList);
        return "setting/code/citizenship/index";
    }
}
