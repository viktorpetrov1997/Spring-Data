package sofuni.exam.service.Impl;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sofuni.exam.models.dto.MoonSeedDTO;
import sofuni.exam.models.dto.MoonSeedRootDTO;
import sofuni.exam.models.entity.Discoverer;
import sofuni.exam.models.entity.Moon;
import sofuni.exam.models.entity.Planet;
import sofuni.exam.models.enums.Type;
import sofuni.exam.repository.DiscovererRepository;
import sofuni.exam.repository.MoonRepository;
import sofuni.exam.repository.PlanetRepository;
import sofuni.exam.service.MoonService;
import sofuni.exam.util.ValidationUtil;
import sofuni.exam.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class MoonServiceImpl implements MoonService
{
    private final MoonRepository moonRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final DiscovererRepository discovererRepository;
    private final PlanetRepository planetRepository;

    private static final String MOON_FILE_PATH = "src/main/resources/files/xml/moons.xml";

    public MoonServiceImpl(MoonRepository moonRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper, DiscovererRepository discovererRepository, PlanetRepository planetRepository)
    {
        this.moonRepository = moonRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.discovererRepository = discovererRepository;
        this.planetRepository = planetRepository;
    }

    @Override
    public boolean areImported()
    {
        return moonRepository.count() > 0;
    }

    @Override
    public String readMoonsFileContent() throws IOException
    {
        return Files.readString(Path.of(MOON_FILE_PATH));
    }

    @Override
    public String importMoons() throws IOException, JAXBException
    {
        MoonSeedRootDTO moonSeedRootDTOS = xmlParser.fromFile(MOON_FILE_PATH, MoonSeedRootDTO.class);

        StringBuilder sb = new StringBuilder();
        for(MoonSeedDTO moonSeedDTO : moonSeedRootDTOS.getMoonSeedDTOList())
        {
            Moon moon = createMoon(moonSeedDTO);
            if(moon == null)
            {
                sb.append(String.format("Invalid moon%n"));
            }
            else
            {
                sb.append(String.format("Successfully imported moon %s%n", moon.getName()));
            }
        }

        return sb.toString();
    }

    private Moon createMoon(MoonSeedDTO moonSeedDTO)
    {
        if(!validationUtil.isValid(moonSeedDTO)) return null;

        Optional<Moon> moonByName = moonRepository.findMoonByName(moonSeedDTO.getName());
        if(moonByName.isPresent())
        {
            return null;
        }

        Optional<Discoverer> discovererOpt = discovererRepository.findById(moonSeedDTO.getDiscoverer());
        Optional<Planet> planetOpt = planetRepository.findById(moonSeedDTO.getPlanet());

        if(discovererOpt.isEmpty() || planetOpt.isEmpty())
        {
            return null;
        }

        try
        {
            Moon moon = modelMapper.map(moonSeedDTO, Moon.class);
            moon.setDiscoverer(discovererOpt.get());
            moon.setPlanet(planetOpt.get());
            moonRepository.save(moon);

            return moon;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @Override
    public String exportMoons()
    {
        StringBuilder sb = new StringBuilder();

        List<Moon> moons = moonRepository.findMoonsByPlanetTypeAndRadiusBetweenOrderByName(Type.GAS_GIANT, 700, 2000);

        for(Moon moon : moons)
        {
            sb.append(String.format(Locale.US, "***Moon %s is a natural satellite of %s and has a radius of %.2f km.%n"
                    , moon.getName(), moon.getPlanet().getName(), moon.getRadius()));
            sb.append(String.format(Locale.US, "****Discovered by %s %s%n%n", moon.getDiscoverer().getFirstName(), moon.getDiscoverer().getLastName()));
        }

        return sb.toString();
    }
}
