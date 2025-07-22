import entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Scanner;

public class ContainsEmployee
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        String[] names = scanner.nextLine().split("\\s+");
        String firstName = names[0];
        String lastName = names[1];

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercises");

        EntityManager em = emf.createEntityManager();

        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee AS e where e.firstName = :fn and e.lastName = :ln", Employee.class)
                .setParameter("fn", firstName)
                .setParameter("ln", lastName);

        List<Employee> employees = query.getResultList();

        if(employees.isEmpty())
        {
            System.out.println("No");
        }
        else
        {
            System.out.println("Yes");
        }
    }
}
