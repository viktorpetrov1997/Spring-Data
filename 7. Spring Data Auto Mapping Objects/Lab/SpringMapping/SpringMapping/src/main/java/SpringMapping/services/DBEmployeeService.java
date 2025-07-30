package SpringMapping.services;

import SpringMapping.DBEmployee;
import SpringMapping.dtos.DBEmployeeDTO;
import SpringMapping.repository.DBEmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class DBEmployeeService
{
    private final DBEmployeeRepository dbEmployeeRepository;
    private final ModelMapper modelMapper;

    public DBEmployeeService(DBEmployeeRepository dbEmployeeRepository, ModelMapper modelMapper)
    {
        this.dbEmployeeRepository = dbEmployeeRepository;
        this.modelMapper = modelMapper;
    }

    public void create()
    {
        DBEmployee byId = dbEmployeeRepository.findById(1L).get();

        DBEmployee dbEmployee = new DBEmployee("Team", "Game", BigDecimal.TEN, byId);

        dbEmployeeRepository.save(dbEmployee);
    }

    public List<DBEmployeeDTO> search(int bornBeforeYear)
    {
        LocalDate cutoff = LocalDate.of(bornBeforeYear, 1, 1);

        List<DBEmployee> dbEmployees = dbEmployeeRepository.findByBirthdayBeforeOrderBySalaryDesc(cutoff);

        DBEmployeeDTO[] employeeDTOs = this.modelMapper.map(dbEmployees, DBEmployeeDTO[].class);

        return Arrays.asList(employeeDTOs);
    }

    public void seed()
    {
        DBEmployee manager = new DBEmployee("Alice", "Smith", new BigDecimal("15000"), LocalDate.of(1965, 5, 10), "123 Main St", null);
        DBEmployee emp1 = new DBEmployee("Bob", "Johnson", new BigDecimal("8000"), LocalDate.of(1985, 3, 20), "456 Oak Ave", manager);
        DBEmployee emp2 = new DBEmployee("Carol", "Williams", new BigDecimal("12000"), LocalDate.of(1975, 11, 5), "789 Pine Rd", manager);
        DBEmployee emp3 = new DBEmployee("David", "Brown", new BigDecimal("5000"), LocalDate.of(1995, 2, 15), "321 Maple Ln", manager);

        dbEmployeeRepository.save(manager);
        dbEmployeeRepository.save(emp1);
        dbEmployeeRepository.save(emp2);
        dbEmployeeRepository.save(emp3);
    }
}
