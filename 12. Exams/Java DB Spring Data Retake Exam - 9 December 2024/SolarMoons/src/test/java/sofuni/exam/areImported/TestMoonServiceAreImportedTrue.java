package sofuni.exam.areImported;
//TestPlanetServiceAreImportedFalse

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sofuni.exam.repository.MoonRepository;
import sofuni.exam.service.Impl.MoonServiceImpl;


@ExtendWith(MockitoExtension.class)
public class TestMoonServiceAreImportedTrue {


    @InjectMocks
    private MoonServiceImpl moonService;
    @Mock
    private MoonRepository moonRepository;

    @Test
    void areImportedShouldReturnFalse() {
        Mockito.when(moonRepository.count()).thenReturn(1L);
        Assertions.assertTrue(moonService.areImported());
    }
}