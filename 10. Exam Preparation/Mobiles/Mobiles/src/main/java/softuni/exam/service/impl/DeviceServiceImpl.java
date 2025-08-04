package softuni.exam.service.impl;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.DeviceDTO;
import softuni.exam.models.dto.DeviceRootDTO;
import softuni.exam.models.entity.Device;
import softuni.exam.models.entity.DeviceType;
import softuni.exam.models.entity.Sale;
import softuni.exam.repository.DeviceRepository;
import softuni.exam.repository.SaleRepository;
import softuni.exam.service.DeviceService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService
{
    private final DeviceRepository deviceRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final SaleRepository saleRepository;

    private static final String DEVICES_FILE_PATH = "src/main/resources/files/xml/devices.xml";

    public DeviceServiceImpl(DeviceRepository deviceRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper, SaleRepository saleRepository)
    {
        this.deviceRepository = deviceRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.saleRepository = saleRepository;
    }

    @Override
    public boolean areImported()
    {
        return deviceRepository.count() > 0;
    }

    @Override
    public String readDevicesFromFile() throws IOException
    {
        return Files.readString(Path.of(DEVICES_FILE_PATH));
    }

    @Override
    public String importDevices() throws IOException, JAXBException
    {
        DeviceRootDTO deviceRootDTO = xmlParser.fromFile(DEVICES_FILE_PATH, DeviceRootDTO.class);

        StringBuilder sb = new StringBuilder();
        for(DeviceDTO deviceDTO : deviceRootDTO.getDeviceDTOS())
        {
            Device device = createDevice(deviceDTO);
            if(device == null)
            {
                sb.append(String.format("Invalid device%n"));
            }
            else
            {
                sb.append(String.format("Successfully imported device of type %s with brand %s%n", device.getDeviceType(), device.getBrand()));
            }
        }

        return sb.toString();
    }

    private Device createDevice(DeviceDTO deviceDTO)
    {
        if(!validationUtil.isValid(deviceDTO)) return null;

        Optional<Device> deviceByNameAndModel = deviceRepository.findDeviceByBrandAndModel(deviceDTO.getBrand(), deviceDTO.getModel());
        if(deviceByNameAndModel.isPresent())
        {
            return null;
        }

        Optional<Sale> saleOptional = saleRepository.findById(deviceDTO.getSale());

        if(saleOptional.isEmpty())
        {
            return null;
        }

        try
        {
            Device device = modelMapper.map(deviceDTO, Device.class);
            deviceRepository.save(device);

            return device;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @Override
    public String exportDevices()
    {
        StringBuilder sb = new StringBuilder();

        List<Device> devices = deviceRepository.exportDevicesByTypePriceAndStorage(DeviceType.SMART_PHONE, 1000.0 , 128);

        for(Device device : devices)
        {
            sb.append(String.format("Device brand: %s%n", device.getBrand()));
            sb.append(String.format(Locale.US, "   *Model: %s%n   **Storage: %d%n   ***Price: %.2f%n",
                    device.getModel(), device.getStorage(), device.getPrice()));
        }

        return sb.toString();
    }
}
