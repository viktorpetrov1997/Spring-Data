package bg.softuni;

import dtos.EmployeeDTO;
import entities.Address;
import entities.Employee;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.math.BigDecimal;

public class Main
{
    private static void fromDbToPresentation()
    {
        Address address = new Address("BG", "Sofia");

        Employee employee = new Employee("Pesho", BigDecimal.TEN, address);

        EmployeeDTO employeeDTO = mapToDTO(employee);

        System.out.println(employeeDTO);
    }

    private static void fromPresentationToDb()
    {
        EmployeeDTO inputData = new EmployeeDTO(10, "Pesho", "Sofia");

        Employee toInsert = new Employee();
        toInsert.setId(inputData.getId());
        toInsert.setFirstName(inputData.getFirstName());

        //Employee altInsert = new Employee(inputData);
    }

    private static EmployeeDTO mapToDTO(Employee employee)
    {
        EmployeeDTO result = new EmployeeDTO();

        result.setId(employee.getId());
        result.setFirstName(employee.getFirstName());
        result.setAddressCity(employee.getAddress().getCity());

        EmployeeDTO altResult = new EmployeeDTO(employee);

        return result;
    }

    public static void main(String[] args)
    {
        ModelMapper modelMapper = new ModelMapper();

        Address address = new Address("BG", "Plovdiv");

        Employee employee = new Employee("Milena", BigDecimal.TEN, address);

        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);

        PropertyMap<Employee, EmployeeDTO> customMap = new PropertyMap<Employee, EmployeeDTO>()
        {
            @Override
            protected void configure()
            {
                map().setId(source.getId());
                map().setFirstName(source.getFirstName());
                map().setAddressCity(source.getAddress().getCountry());
            }
        };

        modelMapper.addMappings(customMap);

        EmployeeDTO custom = modelMapper.map(employee, EmployeeDTO.class);

        ModelMapper second = new ModelMapper();
        EmployeeDTO secondResult = second.map(employee, EmployeeDTO.class);

        System.out.println(custom);
        System.out.println(secondResult);
    }
}