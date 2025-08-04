package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.DealershipSeedDTO;
import softuni.exam.models.entity.Dealership;
import softuni.exam.repository.DealershipRepository;
import softuni.exam.service.DealershipService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class DealershipServiceImpl implements DealershipService
{
    private final DealershipRepository dealershipRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    private static final String DEALERSHIP_FILE_PATH = "src/main/resources/files/json/dealerships.json";

    public DealershipServiceImpl(DealershipRepository dealershipRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper)
    {
        this.dealershipRepository = dealershipRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported()
    {
        return dealershipRepository.count() > 0;
    }

    @Override
    public String readDealershipsFromFile() throws IOException
    {
        return Files.readString(Path.of(DEALERSHIP_FILE_PATH));
    }

    @Override
    public String importDealerships() throws IOException
    {
        DealershipSeedDTO[] dealershipSeedDTOS = gson.fromJson(readDealershipsFromFile(), DealershipSeedDTO[].class);

        StringBuilder sb = new StringBuilder();
        boolean lastWasInvalid = false;

        for(DealershipSeedDTO dealershipSeedDTO : dealershipSeedDTOS)
        {
            Dealership dealership = createDealership(dealershipSeedDTO);

            if(dealership == null)
            {
                if(!lastWasInvalid)
                {
                    sb.append("Invalid dealership\n");
                    lastWasInvalid = true;
                }
            }
            else
            {
                sb.append(String.format("Successfully imported dealership %s%n", dealership.getName()));
                lastWasInvalid = false;
            }
        }

        return sb.toString();
    }

    private Dealership createDealership(DealershipSeedDTO dealershipSeedDTO)
    {
        if(!validationUtil.isValid(dealershipSeedDTO))
        {
            return null;
        }

        Optional<Dealership> dealershipByName = dealershipRepository.findDealershipByName(dealershipSeedDTO.getName());
        if(dealershipByName.isPresent())
        {
            return null;
        }

        try
        {
            Dealership dealership = modelMapper.map(dealershipSeedDTO, Dealership.class);
            dealershipRepository.save(dealership);

            return dealership;
        }
        catch(Exception e)
        {
            return null;
        }
    }
}
