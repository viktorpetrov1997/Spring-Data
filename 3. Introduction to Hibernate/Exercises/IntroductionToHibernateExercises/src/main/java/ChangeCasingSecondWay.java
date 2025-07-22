import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class ChangeCasingSecondWay
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercises");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        try
        {
            Query updateQuery = em.createQuery("UPDATE Town SET name = upper(name) where length(name) <= 5");
            int affectedTowns = updateQuery.executeUpdate();

            em.getTransaction().commit();

            System.out.println(affectedTowns);
        }
        catch(Exception e)
        {
            em.getTransaction().rollback();
        }
    }
}
