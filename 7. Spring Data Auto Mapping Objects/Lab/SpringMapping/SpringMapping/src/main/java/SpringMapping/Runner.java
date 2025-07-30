package SpringMapping;

import SpringMapping.dtos.DBEmployeeDTO;
import SpringMapping.dtos.EmployeeDTO;
import SpringMapping.dtos.ManagerDTO;
import SpringMapping.services.DBEmployeeService;
import SpringMapping.services.EmployeeService;
import SpringMapping.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class Runner implements CommandLineRunner
{
    private final EmployeeService employeeService;
    private final ManagerService managerService;
    private final DBEmployeeService dbEmployeeService;

    @Autowired
    public Runner(EmployeeService employeeService, ManagerService managerService, DBEmployeeService dbEmployeeService)
    {
        this.employeeService = employeeService;
        this.managerService = managerService;
        this.dbEmployeeService = dbEmployeeService;
    }

    @Override
    public void run(String... args) throws Exception
    {
        // 1. Simple Mapping

        /*
        EmployeeDTO employeeDTO = employeeService.findOne();

        System.out.println(employeeDTO);
        */

        /*
        List<EmployeeDTO> employeeDTOS = employeeService.findALl();

        System.out.println(employeeDTOS);
        */


        // 2. Advanced Mapping

        /*
        ManagerDTO managerDTO = managerService.findCEO();

        System.out.println(managerDTO);
        */


        // 3. Projection

        /*
        dbEmployeeService.seed();

        List<DBEmployeeDTO> employees = dbEmployeeService.search(1990);
        employees.forEach(System.out::println);
        */
    }
}
