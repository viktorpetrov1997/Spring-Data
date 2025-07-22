import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.Scanner;

public class ContainsEmployeesSecondWay
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        String[] names = scanner.nextLine().split("\\s+");
        String firstName = names[0];
        String lastName = names[1];

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercises");

        EntityManager em = emf.createEntityManager();

        TypedQuery<Long> query = em.createQuery("SELECT count(e) FROM Employee AS e where e.firstName = :fn and e.lastName = :ln", Long.class)
                .setParameter("fn", firstName)
                .setParameter("ln", lastName);

        long count = query.getSingleResult();

        if(count == 0)
        {
            System.out.println("No");
        }
        else
        {
            System.out.println("Yes");
        }
    }
}
