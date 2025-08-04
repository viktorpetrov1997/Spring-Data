package softuni.exam.service.impl;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.DealerSeedDTO;
import softuni.exam.models.dto.DealerSeedRootDTO;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Dealer;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.DealerRepository;
import softuni.exam.service.DealerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class DealerServiceImpl implements DealerService
{
    private final DealerRepository dealerRepository;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    private static final String DEALER_FILE_PATH = "src/main/resources/files/xml/dealers.xml";

    public DealerServiceImpl(DealerRepository dealerRepository, ValidationUtil validationUtil, XmlParser xmlParser, CarRepository carRepository, ModelMapper modelMapper)
    {
        this.dealerRepository = dealerRepository;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported()
    {
        return dealerRepository.count() > 0;
    }

    @Override
    public String readDealersFromFile() throws IOException
    {
        return Files.readString(Path.of(DEALER_FILE_PATH));
    }

    @Override
    public String importDealers() throws IOException, JAXBException
    {
        DealerSeedRootDTO dealerSeedRootDTO = xmlParser.fromFile(DEALER_FILE_PATH, DealerSeedRootDTO.class);

        StringBuilder sb = new StringBuilder();
        for(DealerSeedDTO dealerSeedDTO : dealerSeedRootDTO.getDealerSeedDTOS())
        {
            Dealer dealer = createDealer(dealerSeedDTO);
            if(dealer == null)
            {
                sb.append(String.format("Invalid dealer%n"));
            }
            else
            {
                sb.append(String.format("Successfully imported dealer %s %s%n", dealer.getFirstName(), dealer.getLastName()));
            }
        }

        return sb.toString();
    }

    private Dealer createDealer(DealerSeedDTO dealerSeedDTO)
    {
        if(!validationUtil.isValid(dealerSeedDTO)) return null;

        Optional<Dealer> dealerByFirstNameAndLastName = dealerRepository
                .findDealerByFirstNameAndLastName(dealerSeedDTO.getFirstName(), dealerSeedDTO.getLastName());
        if(dealerByFirstNameAndLastName.isPresent())
        {
            return null;
        }

        Optional<Car> carOptional = carRepository.findById(dealerSeedDTO.getOfferingCar());

        if(carOptional.isEmpty())
        {
            return null;
        }

        try
        {
            Dealer dealer = modelMapper.map(dealerSeedDTO, Dealer.class);
            dealer.setOfferingCar(carOptional.get());
            dealerRepository.save(dealer);

            return dealer;
        }
        catch(Exception e)
        {
            return null;
        }
    }
}
