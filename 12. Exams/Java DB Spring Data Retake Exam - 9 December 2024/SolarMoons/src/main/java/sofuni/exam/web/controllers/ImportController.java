package sofuni.exam.web.controllers;

import jakarta.xml.bind.JAXBException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sofuni.exam.service.DiscovererService;
import sofuni.exam.service.MoonService;
import sofuni.exam.service.PlanetService;

import java.io.IOException;


@Controller
@RequestMapping("/import")
public class ImportController extends BaseController {

    private PlanetService planetService;
    private MoonService moonService;
    private DiscovererService discovererService;


    public ImportController(PlanetService planetService, MoonService moonService, DiscovererService discovererService) {

        this.planetService = planetService;
        this.moonService = moonService;
        this.discovererService = discovererService;
    }

    @GetMapping("/json")
    public ModelAndView importJson() {

        boolean[] areImported = new boolean[]{
                this.planetService.areImported(),
                this.discovererService.areImported()
        };

        return super.view("json/import-json", "areImported", areImported);
    }

    @GetMapping("/xml")
    public ModelAndView importXml() {
        boolean[] areImported = new boolean[]{
                this.moonService.areImported()
        };

        return super.view("xml/import-xml", "areImported", areImported);
    }

    @GetMapping("/planets")
    public ModelAndView importPlanets() throws IOException {
        String fileContent = this.planetService.readPlanetsFileContent();
        return super.view("json/import-planets", "planets", fileContent);
    }

    @PostMapping("/planets")
    public ModelAndView importPlanetsConfirm() throws IOException {
        System.out.println(this.planetService.importPlanets());

        return super.redirect("/import/json");
    }

    @GetMapping("/discoverers")
    public ModelAndView importDiscoverers() throws IOException {
        String fileContent = this.discovererService.readDiscovererFileContent();
        return super.view("json/import-discoverers", "discoverers", fileContent);
    }

    @PostMapping("/discoverers")
    public ModelAndView importDiscoverersConfirm() throws IOException, JAXBException {
        System.out.println(this.discovererService.importDiscoverers());

        return super.redirect("/import/json");
    }


    @GetMapping("/moons")
    public ModelAndView importMoons() throws IOException, JAXBException {
        String fileContent = this.moonService.readMoonsFileContent();

        return super.view("xml/import-moons", "moons", fileContent);
    }

    @PostMapping("/moons")
    public ModelAndView importMoonsConfirm() throws IOException, JAXBException {
        System.out.println(this.moonService.importMoons());

        return super.redirect("/import/xml");
    }

}
