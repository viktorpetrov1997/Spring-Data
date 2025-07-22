import entities.Town;
import jakarta.persistence.*;

import java.util.List;

public class ChangeCasing
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercises");

        EntityManager em = emf.createEntityManager();

        TypedQuery<Town> query = em.createQuery("SELECT t FROM Town AS t", Town.class);

        List<Town> towns = query.getResultList();

        em.getTransaction().begin();

        try
        {
            for(Town town : towns)
            {
                if(town.getName().length() > 5)
                {
                    em.detach(town);
                    // The detach() here ensures that only towns with names of 5 characters or fewer are updated in the database.
                    // Longer ones are explicitly ignored by removing them from the persistence context.
                }
                else
                {
                    town.setName(town.getName().toUpperCase());
                }
            }

            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            em.getTransaction().rollback();
        }
    }
}
