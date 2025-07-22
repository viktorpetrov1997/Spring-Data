import entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Scanner;

public class FindEmployeesByFirstName
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        String pattern = scanner.nextLine();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercises");

        EntityManager em = emf.createEntityManager();

        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee AS e WHERE e.firstName LIKE :pattern", Employee.class)
                .setParameter("pattern", pattern + "%");

        List<Employee> employees = query.getResultList();

        for(Employee employee : employees)
        {
            System.out.printf("%s %s - %s - ($%.2f)%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle(), employee.getSalary());
        }
    }
}
