package sofuni.exam.importPlanets;
//TestImportPlanetsZeroTest000

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import sofuni.exam.service.Impl.PlanetServiceImpl;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestImportPlanetsZeroTest000 {

    @Autowired
    private PlanetServiceImpl planetService;

    @Test
    void importPlanetsZeroTest() throws JAXBException, IOException {
            String expected = """
                    Successfully imported planet Mercury
                    Invalid planet
                    Invalid planet
                    Successfully imported planet Venus
                    Successfully imported planet Earth
                    Successfully imported planet Mars
                    Successfully imported planet Jupiter
                    Successfully imported planet Saturn
                    Successfully imported planet Uranus
                    Successfully imported planet Neptune
                    Successfully imported planet Pluto""";


        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        String actual = planetService.importPlanets();
        String[] actualSplit = actual.split("\\r\\n?|\\n");

        Assertions.assertArrayEquals(expectedSplit, actualSplit);
    }
}

