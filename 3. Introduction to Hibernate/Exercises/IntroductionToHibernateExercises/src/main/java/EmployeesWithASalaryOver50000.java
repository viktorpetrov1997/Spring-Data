import jakarta.persistence.*;

import java.util.List;

public class EmployeesWithASalaryOver50000
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercises");

        EntityManager em = emf.createEntityManager();

        TypedQuery<String> query = em.createQuery("SELECT firstName FROM Employee WHERE salary > 50000", String.class);

        List<String> result = query.getResultList();

        result.forEach(System.out::println);
    }
}
