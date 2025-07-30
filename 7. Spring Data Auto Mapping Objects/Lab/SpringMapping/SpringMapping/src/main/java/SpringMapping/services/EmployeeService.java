package SpringMapping.services;

import SpringMapping.dtos.EmployeeDTO;
import SpringMapping.entities.Address;
import SpringMapping.entities.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService
{
    private final ModelMapper modelMapper;

    public EmployeeService(ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO findOne()
    {
        Address address = new Address("BG", "Ruse");

        Employee employee = new Employee("Pesho", "Goshov", BigDecimal.TEN, address);

        return this.modelMapper.map(employee, EmployeeDTO.class);
    }

    public List<EmployeeDTO> findALl()
    {
        Address address = new Address("BG", "Ruse");

        List<Employee> dbAll = new ArrayList<>();
        dbAll.add(new Employee("Pesho", "Goshov", BigDecimal.TEN, address));
        dbAll.add(new Employee("Pesho2", "Goshov2", BigDecimal.ONE, address));
        dbAll.add(new Employee("Pesho3", "Goshov3", BigDecimal.ZERO, address));

        EmployeeDTO[] employeeDTOS = this.modelMapper.map(dbAll, EmployeeDTO[].class);

        return Arrays.asList(employeeDTOS);
    }
}
