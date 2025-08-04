package sofuni.exam.service.Impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sofuni.exam.models.dto.DiscovererSeedDTO;
import sofuni.exam.models.entity.Discoverer;
import sofuni.exam.models.entity.Planet;
import sofuni.exam.repository.DiscovererRepository;
import sofuni.exam.service.DiscovererService;
import sofuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class DiscovererServiceImpl implements DiscovererService
{
    private final DiscovererRepository discovererRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    private static final String DISCOVERER_FILE_PATH = "src/main/resources/files/json/discoverers.json";

    public DiscovererServiceImpl(DiscovererRepository discovererRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper)
    {
        this.discovererRepository = discovererRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported()
    {
        return discovererRepository.count() > 0;
    }

    @Override
    public String readDiscovererFileContent() throws IOException
    {
        return Files.readString(Path.of(DISCOVERER_FILE_PATH));
    }

    @Override
    public String importDiscoverers() throws IOException
    {
        DiscovererSeedDTO[] discovererSeedDTOS = gson.fromJson(readDiscovererFileContent(), DiscovererSeedDTO[].class);

        StringBuilder sb = new StringBuilder();
        for(DiscovererSeedDTO discovererSeedDTO : discovererSeedDTOS)
        {
            Discoverer discoverer = createDiscoverer(discovererSeedDTO);
            if(discoverer == null)
            {
                sb.append(String.format("Invalid discoverer%n"));
            }
            else
            {
                sb.append(String.format("Successfully imported discoverer %s %s%n", discoverer.getFirstName(), discoverer.getLastName()));
            }
        }

        return sb.toString();
    }

    private Discoverer createDiscoverer(DiscovererSeedDTO discovererSeedDTO)
    {
        if(!validationUtil.isValid(discovererSeedDTO)) return null;

        Optional<Discoverer> discovererByFirstNameAndLastName = discovererRepository.findByFirstNameAndLastName(discovererSeedDTO.getFirstName(), discovererSeedDTO.getLastName());
        if(discovererByFirstNameAndLastName.isPresent())
        {
            return null;
        }

        try
        {
            Discoverer discoverer = modelMapper.map(discovererSeedDTO, Discoverer.class);
            discovererRepository.save(discoverer);

            return discoverer;
        }
        catch(Exception e)
        {
            return null;
        }
    }
}
