package sofuni.exam.areImported;
//TestPlanetServiceAreImportedFalse
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sofuni.exam.repository.PlanetRepository;
import sofuni.exam.service.Impl.PlanetServiceImpl;


@ExtendWith(MockitoExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestPlanetServiceAreImportedTrue {


    @InjectMocks
    private PlanetServiceImpl planetService;
    @Mock
    private PlanetRepository mockPlanetRepository;


    @Test
    void areImportedShouldReturnFalse() {
        Mockito.when(mockPlanetRepository.count()).thenReturn(1L);
        Assertions.assertTrue(planetService.areImported());
    }
}