package SpringMapping.services;

import SpringMapping.dtos.ManagerDTO;
import SpringMapping.entities.Address;
import SpringMapping.entities.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerService
{
    private final ModelMapper modelMapper;

    public ManagerService(ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }

    public ManagerDTO findCEO()
    {
        Address address = new Address("BG", "Burgas");

        List<Employee> dbTeam = new ArrayList<>();
        dbTeam.add(new Employee("Pesho", "Goshov", BigDecimal.TEN, address));
        dbTeam.add(new Employee("Pesho2", "Goshov2", BigDecimal.ONE, address));
        dbTeam.add(new Employee("Pesho3", "Goshov3", BigDecimal.ZERO, address));

        Employee CEO = new Employee("Manager", "Manager Last", BigDecimal.TEN, address
        , false, null, dbTeam, "");

        return modelMapper.map(CEO, ManagerDTO.class);
    }
}
