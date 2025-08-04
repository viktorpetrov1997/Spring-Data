package sofuni.exam.importMoons;
//TestImportMoonsWithDuplicateName001

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import sofuni.exam.service.Impl.MoonServiceImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestImportMoonsWithDuplicateName001 {

    @Autowired
    private MoonServiceImpl moonService;
    @Sql({"/planets-test-imports.sql", "/discoverers-test-imports.sql"})

    @Test
    void importMoonsInvalidEntries001() throws IOException, JAXBException {
        String expected = """
                Successfully imported moon Charon
                Invalid moon""";

        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        copyRewriteFileForTest();
        String actual = moonService.importMoons();
        String[] actualSplit = actual.split("\\r\\n?|\\n");

        returnOriginalValue();
        Assertions.assertArrayEquals(expectedSplit, actualSplit);
    }

    private void copyRewriteFileForTest() throws IOException {
        File originalJsonFile = new File("src/main/resources/files/xml/moons.xml");

        String testJSON = """
                <?xml version='1.0' encoding='UTF-8'?>
                <moons>
                   <moon>
                       <name>Charon</name>
                       <discovered>1978-06-22</discovered>
                       <distance_from_planet>17536</distance_from_planet>
                       <radius>606</radius>
                       <discoverer_id>8</discoverer_id>
                       <planet_id>9</planet_id>
                   </moon>
                   <moon>
                       <name>Charon</name>
                       <discovered>1978-06-22</discovered>
                       <distance_from_planet>17536</distance_from_planet>
                       <radius>606</radius>
                       <discoverer_id>8</discoverer_id>
                       <planet_id>9</planet_id>
                   </moon>
                </moons>""";


        try {
            FileWriter f2 = new FileWriter(originalJsonFile, false);
            f2.write(testJSON);
            f2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void returnOriginalValue() {
        File originalJsonFileSrc = new File("src/main/resources/files/xml/moons.xml");

        try {
            FileWriter f2 = new FileWriter(originalJsonFileSrc, false);
            String testOriginalFile = Files.readString(Path.of("src/test/resources/original-files/moons.xml"));
            f2.write(testOriginalFile);
            f2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

