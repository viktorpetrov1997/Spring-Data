package sofuni.exam.importPlanets;
//TestImportPlanetsWithInvalidName002

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import sofuni.exam.service.Impl.PlanetServiceImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestImportPlanetsWithInvalidName002 {

    @Autowired
    private PlanetServiceImpl planetService;


    @Test
    void importPlanetInvalidEntries002() throws IOException {
        String expected = """
                Invalid planet
                Successfully imported planet Venus""";

        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        copyRewriteFileForTest();
        String actual = planetService.importPlanets();
        String[] actualSplit = actual.split("\\r\\n?|\\n");

        returnOriginalValue();
        Assertions.assertArrayEquals(expectedSplit, actualSplit);
    }

    private void copyRewriteFileForTest() throws IOException {
        File originalJsonFile = new File("src/main/resources/files/json/planets.json");

        String testJSON = """
                [
                  {
                    "name": "Ve",
                    "diameter": 12104,
                    "distanceFromSun": 108200000,
                    "orbitalPeriod": 224.7,
                    "type": "TERRESTRIAL"
                  },
                  {
                     "name": "Venus",
                     "diameter": 12104,
                     "distanceFromSun": 108200000,
                     "orbitalPeriod": 224.7,
                     "type": "TERRESTRIAL"
                    }
                ]""";


        try {
            FileWriter f2 = new FileWriter(originalJsonFile, false);
            f2.write(testJSON);
            f2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void returnOriginalValue() {
        File originalJsonFileSrc = new File("src/main/resources/files/json/planets.json");

        try {
            FileWriter f2 = new FileWriter(originalJsonFileSrc, false);
            String testOriginalFile = Files.readString(Path.of("src/test/resources/original-files/planets.json"));
            f2.write(testOriginalFile);
            f2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
