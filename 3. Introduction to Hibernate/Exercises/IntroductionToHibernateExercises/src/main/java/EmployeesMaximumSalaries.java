import dtos.DepartmentSummaryDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EmployeesMaximumSalaries
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercises");

        EntityManager em = emf.createEntityManager();

        TypedQuery<DepartmentSummaryDto> query = em.createQuery("SELECT new dtos.DepartmentSummaryDto(d.name, MAX(e.salary)) FROM Department d JOIN d.employees e GROUP BY d.id, d.name HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000", DepartmentSummaryDto.class);

        List<DepartmentSummaryDto> departments = query.getResultList();

        for(DepartmentSummaryDto department : departments)
        {
            System.out.printf("%s %.2f%n", department.getName(), department.getMaxSalary());
        }
    }
}
