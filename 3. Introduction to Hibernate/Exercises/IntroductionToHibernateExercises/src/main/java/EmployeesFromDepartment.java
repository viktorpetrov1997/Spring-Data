import entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EmployeesFromDepartment
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercises");

        EntityManager em = emf.createEntityManager();

        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.department.name = 'Research and Development' ORDER BY e.salary ASC, e.id ASC", Employee.class);

        List<Employee> employees = query.getResultList();

        for(Employee employee : employees)
        {
            System.out.printf("%s %s from %s - $%.2f%n", employee.getFirstName(), employee.getLastName(), employee.getDepartment().getName(), employee.getSalary());
        }
    }
}
