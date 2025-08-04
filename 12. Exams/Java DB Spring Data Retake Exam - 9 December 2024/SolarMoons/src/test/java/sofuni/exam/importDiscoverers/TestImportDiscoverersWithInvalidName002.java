package sofuni.exam.importDiscoverers;
//TestImportDiscoverersWithInvalidName002

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import sofuni.exam.service.Impl.DiscovererServiceImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestImportDiscoverersWithInvalidName002 {

    @Autowired
    private DiscovererServiceImpl discovererService;

    @Test
    void importDiscoverersInvalidEntries002() throws IOException {
        String expected = """
                Invalid discoverer
                Successfully imported discoverer Galileo Galilei""";

        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        copyRewriteFileForTest();
        String actual = discovererService.importDiscoverers();
        String[] actualSplit = actual.split("\\r\\n?|\\n");

        returnOriginalValue();
        Assertions.assertArrayEquals(expectedSplit, actualSplit);
    }

    private void copyRewriteFileForTest() throws IOException {
        File originalJsonFile = new File("src/main/resources/files/json/discoverers.json");

        String testJSON = """
                [
                  {
                   "firstName": "G",
                   "lastName": "Galilei",
                   "nationality": "Italian",
                   "occupation": "astronomer"
                     },
                   {
                   "firstName": "Galileo",
                   "lastName": "Galilei",
                   "nationality": "Italian",
                   "occupation": "astronomer"
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
        File originalJsonFileSrc = new File("src/main/resources/files/json/discoverers.json");

        try {
            FileWriter f2 = new FileWriter(originalJsonFileSrc, false);
            String testOriginalFile = Files.readString(Path.of("src/test/resources/original-files/discoverers.json"));
            f2.write(testOriginalFile);
            f2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

