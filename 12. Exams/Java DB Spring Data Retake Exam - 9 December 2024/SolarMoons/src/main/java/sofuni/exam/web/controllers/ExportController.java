package sofuni.exam.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sofuni.exam.service.MoonService;

@Controller
@RequestMapping("/export")
public class ExportController extends BaseController {

    private final MoonService moonService;

    public ExportController(MoonService moonService) {
        this.moonService = moonService;
    }

    @GetMapping("moons")
    public ModelAndView exportMoons() {
        String moons = this.moonService.exportMoons();

        return super.view("export/export-moons", "moons", moons);
    }
}
