package sofuni.exam.readFile;
//TestPlanetServiceReadFromFile

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import sofuni.exam.service.Impl.PlanetServiceImpl;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TesPlanetServiceReadFromFile {

    @Autowired
    private PlanetServiceImpl planetService;

    @Test
    void readPlanetsFromFile() throws IOException {

        String expected = """
                [
                  {
                    "name": "Mercury",
                    "diameter": 4879,
                    "distanceFromSun": 57910000,
                    "orbitalPeriod": 87.97,
                    "type": "TERRESTRIAL"
                  },
                  {
                    "name": "Mercury",
                    "diameter": 4879,
                    "distanceFromSun": 57910000,
                    "orbitalPeriod": 87.97,
                    "type": "TERRESTRIAL"
                  },
                  {
                    "name": "Me",
                    "diameter": 4879,
                    "distanceFromSun": 57910000,
                    "orbitalPeriod": 87.97,
                    "type": "TERRESTRIAL"
                  },
                  {
                    "name": "Venus",
                    "diameter": 12104,
                    "distanceFromSun": 108200000,
                    "orbitalPeriod": 224.7,
                    "type": "TERRESTRIAL"
                  },
                  {
                    "name": "Earth",
                    "diameter": 12742,
                    "distanceFromSun": 149000000,
                    "orbitalPeriod": 365.26,
                    "type": "TERRESTRIAL"
                  },
                  {
                    "name": "Mars",
                    "diameter": 6779,
                    "distanceFromSun": 227900000,
                    "orbitalPeriod": 687,
                    "type": "TERRESTRIAL"
                  },
                  {
                    "name": "Jupiter",
                    "diameter": 139820,
                    "distanceFromSun": 778500000,
                    "orbitalPeriod": 4331,
                    "type": "GAS_GIANT"
                  },
                  {
                    "name": "Saturn",
                    "diameter": 116460,
                    "distanceFromSun": 1434000000,
                    "orbitalPeriod": 10747,
                    "type": "GAS_GIANT"
                  },
                  {
                    "name": "Uranus",
                    "diameter": 50724,
                    "distanceFromSun": 2871000000,
                    "orbitalPeriod": 30589,
                    "type": "ICE_GIANT"
                  },
                  {
                    "name": "Neptune",
                    "diameter": 49244,
                    "distanceFromSun": 4495000000,
                    "orbitalPeriod": 59800,
                    "type": "ICE_GIANT"
                  },
                  {
                    "name": "Pluto",
                    "diameter": 2376,
                    "distanceFromSun": 5906000000,
                    "orbitalPeriod": 90560,
                    "type": "DWARF_PLANET"
                  }
                ]""";

        String actual = planetService.readPlanetsFileContent()
                .replaceAll("\\r\\n|\\r|\\n", "\n").trim();
        Assertions.assertEquals(expected, actual);
    }
}