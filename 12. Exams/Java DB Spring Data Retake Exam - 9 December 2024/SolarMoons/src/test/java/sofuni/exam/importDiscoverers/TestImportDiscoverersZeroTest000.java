package sofuni.exam.importDiscoverers;
//TestImportDiscoverersZeroTest000

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import sofuni.exam.service.Impl.DiscovererServiceImpl;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestImportDiscoverersZeroTest000 {

    @Autowired
    private DiscovererServiceImpl discovererService;

    @Test
    void importDiscoverersZeroTest() throws JAXBException, IOException {
            String expected = """
                    Successfully imported discoverer Galileo Galilei
                    Invalid discoverer
                    Invalid discoverer
                    Successfully imported discoverer Christiaan Huygens
                    Successfully imported discoverer Giovanni Cassini
                    Successfully imported discoverer William Herschel
                    Successfully imported discoverer Gerard Kuiper
                    Successfully imported discoverer Asaph Hall
                    Successfully imported discoverer William Lassell
                    Successfully imported discoverer James Christy
                    Successfully imported discoverer Stephen Synnott""";


        String[] expectedSplit = expected.split("\\r\\n?|\\n");

        String actual = discovererService.importDiscoverers();
        String[] actualSplit = actual.split("\\r\\n?|\\n");

        Assertions.assertArrayEquals(expectedSplit, actualSplit);
    }
}

