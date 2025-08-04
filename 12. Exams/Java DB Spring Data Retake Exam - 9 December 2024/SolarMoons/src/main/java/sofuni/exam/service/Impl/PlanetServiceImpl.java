package sofuni.exam.service.Impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sofuni.exam.models.dto.PlanetSeedDTO;
import sofuni.exam.models.entity.Planet;
import sofuni.exam.repository.PlanetRepository;
import sofuni.exam.service.PlanetService;
import sofuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class PlanetServiceImpl implements PlanetService
{
    private final PlanetRepository planetRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    private static final String PLANET_FILE_PATH = "src/main/resources/files/json/planets.json";

    public PlanetServiceImpl(PlanetRepository planetRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper)
    {
        this.planetRepository = planetRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported()
    {
        return planetRepository.count() > 0;
    }

    @Override
    public String readPlanetsFileContent() throws IOException
    {
        return Files.readString(Path.of(PLANET_FILE_PATH));
    }

    @Override
    public String importPlanets() throws IOException
    {
        PlanetSeedDTO[] planetSeedDTOS = gson.fromJson(readPlanetsFileContent(), PlanetSeedDTO[].class);

        StringBuilder sb = new StringBuilder();
        for(PlanetSeedDTO planetSeedDTO : planetSeedDTOS)
        {
            Planet planet = createPlanet(planetSeedDTO);
            if(planet == null)
            {
                sb.append(String.format("Invalid planet%n"));
            }
            else
            {
                sb.append(String.format("Successfully imported planet %s%n", planet.getName()));
            }
        }

        return sb.toString();
    }

    private Planet createPlanet(PlanetSeedDTO planetSeedDTO)
    {
        if(!validationUtil.isValid(planetSeedDTO)) return null;

        Optional<Planet> planetByName = planetRepository.findPlanetByName(planetSeedDTO.getName());
        if(planetByName.isPresent())
        {
            return null;
        }

        try
        {
            Planet planet = modelMapper.map(planetSeedDTO, Planet.class);
            planetRepository.save(planet);

            return planet;
        }
        catch(Exception e)
        {
            return null;
        }
    }
}
