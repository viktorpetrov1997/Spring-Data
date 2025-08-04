package sofuni.exam.web.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import sofuni.exam.service.DiscovererService;
import sofuni.exam.service.MoonService;
import sofuni.exam.service.PlanetService;


@Controller
public class HomeController extends BaseController {

    private PlanetService planetService;
    private MoonService moonService;
    private DiscovererService discovererService;

    public HomeController(PlanetService planetService, MoonService moonService, DiscovererService discovererService) {

        this.planetService = planetService;
        this.moonService = moonService;
        this.discovererService = discovererService;
    }

    @GetMapping("/")
    public ModelAndView index() {
        boolean areImported = this.planetService.areImported() &&
                this.moonService.areImported() &&
                this.discovererService.areImported();

        return super.view("index", "areImported", areImported);
    }
}
