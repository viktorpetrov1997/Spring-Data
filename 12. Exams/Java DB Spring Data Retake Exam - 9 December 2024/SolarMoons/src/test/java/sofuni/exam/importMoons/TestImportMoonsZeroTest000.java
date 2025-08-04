package sofuni.exam.importMoons;
//TestImportMoonsZeroTest000

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import sofuni.exam.service.Impl.MoonServiceImpl;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestImportMoonsZeroTest000 {

    @Autowired
    private MoonServiceImpl moonService;
    @Sql({"/planets-test-imports.sql", "/discoverers-test-imports.sql"})

    @Test
    void importMoonsZeroTest() throws JAXBException, IOException {
            String expected = """
                    Successfully imported moon Moon
                    Invalid moon
                    Successfully imported moon Phobos
                    Invalid moon
                    Successfully imported moon Deimos
                    Successfully imported moon Io
                    Successfully imported moon Europa
                    Successfully imported moon Ganymede
                    Successfully imported moon Callisto
                    Successfully imported moon Titan
                    Successfully imported moon Rhea
                    Successfully imported moon Iapetus
                    Successfully imported moon Tethys
                    Successfully imported moon Dione
                    Successfully imported moon Titania
                    Successfully imported moon Oberon
                    Successfully imported moon Ariel
                    Successfully imported moon Umbriel
                    Successfully imported moon Miranda
                    Successfully imported moon Triton
                    Successfully imported moon Nereid
                    Successfully imported moon Galatea
                    Successfully imported moon Charon""";


        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        String actual = moonService.importMoons();
        String[] actualSplit = actual.split("\\r\\n?|\\n");

        Assertions.assertArrayEquals(expectedSplit, actualSplit);
    }
}

