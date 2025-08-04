package sofuni.exam.areImported;
//TestPlanetServiceAreImportedFalse

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sofuni.exam.repository.DiscovererRepository;
import sofuni.exam.service.Impl.DiscovererServiceImpl;


@ExtendWith(MockitoExtension.class)
public class TestDiscovererServiceAreImportedTrue {


    @InjectMocks
    private DiscovererServiceImpl discovererService;
    @Mock
    private DiscovererRepository discovererRepository;

    @Test
    void areImportedShouldReturnFalse() {
        Mockito.when(discovererRepository.count()).thenReturn(1L);
        Assertions.assertTrue(discovererService.areImported());
    }
}