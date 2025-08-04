package sofuni.exam.export;
//TestMoonsServiceExport001

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import sofuni.exam.service.Impl.MoonServiceImpl;

import java.io.IOException;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestMoonsServiceExport001 {

    @Autowired
    private MoonServiceImpl moonService;

    @Sql({"/export-tests.sql"})
    @Test
    void exportDevices() throws IOException {
        String actual = moonService.exportMoons();

        String expected = """
                ***Moon Europa is a natural satellite of Jupiter and has a radius of 1560.80 km.
                ****Discovered by Galileo Galilei
                                
                ***Moon Iapetus is a natural satellite of Saturn and has a radius of 734.50 km.
                ****Discovered by Giovanni Cassini
                                
                ***Moon Io is a natural satellite of Jupiter and has a radius of 1821.60 km.
                ****Discovered by Galileo Galilei
                                
                ***Moon Rhea is a natural satellite of Saturn and has a radius of 763.80 km.
                ****Discovered by Giovanni Cassini""";

        String[] actualSplit = actual.split("\\r\\n?|\\n");
        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        Assertions.assertArrayEquals(expectedSplit, actualSplit);
    }
}
