package sofuni.exam.areImported;
//TestPlanetServiceAreImportedFalse

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import sofuni.exam.repository.PlanetRepository;
import sofuni.exam.service.Impl.PlanetServiceImpl;


//@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestPlanetServiceAreImportedFalse {


    @InjectMocks
    private PlanetServiceImpl planetService;
    @Mock
    private PlanetRepository planetRepository;

    @Test
    void areImportedShouldReturnFalse() {
        Mockito.when(planetRepository.count()).thenReturn(0L);
//        boolean areImported = planetRepository.count() == 0;
        Assertions.assertFalse(planetService.areImported());
    }
}