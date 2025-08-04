package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CarSeedDTO;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.CarType;
import softuni.exam.models.entity.Dealership;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.DealershipRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService
{
    private final CarRepository carRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final DealershipRepository dealershipRepository;

    private static final String CAR_FILE_PATH = "src/main/resources/files/json/cars.json";

    public CarServiceImpl(CarRepository carRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, DealershipRepository dealershipRepository)
    {
        this.carRepository = carRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.dealershipRepository = dealershipRepository;
    }

    @Override
    public boolean areImported()
    {
        return carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException
    {
        return Files.readString(Path.of(CAR_FILE_PATH));
    }

    @Override
    public String importCars() throws IOException
    {
        CarSeedDTO[] carSeedDTOS = gson.fromJson(readCarsFileContent(), CarSeedDTO[].class);

        StringBuilder sb = new StringBuilder();

        for(CarSeedDTO carSeedDTO : carSeedDTOS)
        {
            Car car = createCar(carSeedDTO);

            if(car == null)
            {
                sb.append("Invalid car\n");
            }
            else
            {
                sb.append(String.format("Successfully imported car %s - %d km.%n", car.getBrand(), car.getMileage()));
            }
        }

        return sb.toString();
    }

    private Car createCar(CarSeedDTO carSeedDTO)
    {
        if(!validationUtil.isValid(carSeedDTO)) return null;

        Optional<Car> carByVin = carRepository.findCarByVin(carSeedDTO.getVin());
        if(carByVin.isPresent())
        {
            return null;
        }

        Optional<Dealership> dealershipOptional = dealershipRepository.findById(carSeedDTO.getDealership());

        if(dealershipOptional.isEmpty())
        {
            return null;
        }

        try
        {
            Car car = modelMapper.map(carSeedDTO, Car.class);
            car.setDealership(dealershipOptional.get());
            carRepository.save(car);

            return car;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @Override
    public String exportCars()
    {
        StringBuilder sb = new StringBuilder();

        List<Car> cars = carRepository.findCarByCarTypeAndMileageLessThanOrderByMileageDesc(CarType.COMBI, 100000);

        for(Car car : cars)
        {
            sb.append(String.format("Brand: %s%n", car.getBrand()));
            sb.append(String.format("   *Mileage: %d km.%n   **Model: %s%n   ***Dealership: %s%n",
                    car.getMileage(), car.getModel(), car.getDealership().getName()));
        }

        return sb.toString();
    }
}
