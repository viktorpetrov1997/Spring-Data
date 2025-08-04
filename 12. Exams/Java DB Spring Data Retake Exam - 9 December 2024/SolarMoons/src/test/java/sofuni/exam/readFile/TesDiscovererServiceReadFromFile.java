package sofuni.exam.readFile;
//TestDiscovererServiceReadFromFile

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import sofuni.exam.service.Impl.DiscovererServiceImpl;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TesDiscovererServiceReadFromFile {

    @Autowired
    private DiscovererServiceImpl discovererService;

    @Test
    void readDiscoverersFromFile() throws IOException {

        String expected = """
                [
                  {
                    "firstName": "Galileo",
                    "lastName": "Galilei",
                    "nationality": "Italian",
                    "occupation": "astronomer"
                  },
                  {
                    "firstName": "Galileo",
                    "lastName": "Galilei",
                    "nationality": "Italian",
                    "occupation": "astronomer"
                  },
                  {
                    "firstName": "C",
                    "lastName": "Huygens",
                    "nationality": "Dutch",
                    "occupation": "mathematician"
                  },
                  {
                    "firstName": "Christiaan",
                    "lastName": "Huygens",
                    "nationality": "Dutch",
                    "occupation": "mathematician"
                  },
                  {
                    "firstName": "Giovanni",
                    "lastName": "Cassini",
                    "nationality": "Italian",
                    "occupation": "astronomer"
                  },
                  {
                    "firstName": "William",
                    "lastName": "Herschel",
                    "nationality": "German-British",
                    "occupation": "astronomer"
                  },
                  {
                    "firstName": "Gerard",
                    "lastName": "Kuiper",
                    "nationality": "Dutch-American",
                    "occupation": "planetary scientist"
                  },
                  {
                    "firstName": "Asaph",
                    "lastName": "Hall",
                    "nationality": "American",
                    "occupation": "mathematician"
                  },
                  {
                    "firstName": "William",
                    "lastName": "Lassell",
                    "nationality": "British",
                    "occupation": "merchant"
                  },
                  {
                    "firstName": "James",
                    "lastName": "Christy",
                    "nationality": "American",
                    "occupation": "astronomer"
                  },
                  {
                    "firstName": "Stephen",
                    "lastName": "Synnott",
                    "nationality": "American",
                    "occupation": "scientist"
                  }
                ]""";

        String actual = discovererService.readDiscovererFileContent()
                .replaceAll("\\r\\n|\\r|\\n", "\n").trim();
        Assertions.assertEquals(expected, actual);
    }
}