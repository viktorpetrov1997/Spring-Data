import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RemoveTowns
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        String townName = scanner.nextLine();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercises");

        EntityManager em = emf.createEntityManager();

        Optional<Integer> townId;
        int deletedAddresses = 0;

        TypedQuery<Integer> townIdQuery = em.createQuery("SELECT t.id FROM Town AS t WHERE t.name = :name", Integer.class)
                        .setParameter("name", townName);

        try
        {
            townId = Optional.of(townIdQuery.getSingleResult());
        }
        catch(NoResultException e)
        {
            townId = Optional.empty();
        }

        if(townId.isPresent())
        {
            TypedQuery<Integer> addressIdsQuery = em.createQuery("SELECT a.id FROM Address AS a WHERE a.town.id = :id", Integer.class)
                            .setParameter("id", townId.get());
            List<Integer> addressIds = addressIdsQuery.getResultList();

            deletedAddresses = addressIds.size();

            em.getTransaction().begin();

            Query updateEmployeesQuery = em.createQuery("UPDATE Employee AS e SET e.address = null WHERE e.address.id IN (:ids)")
                            .setParameter("ids", addressIds);
            updateEmployeesQuery.executeUpdate();

            Query deleteAddressesQuery = em.createQuery("DELETE FROM Address AS a WHERE a.id IN (:ids)")
                            .setParameter("ids", addressIds);
            deleteAddressesQuery.executeUpdate();

            Query deleteTownQuery = em.createQuery("DELETE FROM Town AS t WHERE t.id = :id")
                            .setParameter("id", townId.get());
            deleteTownQuery.executeUpdate();

            em.getTransaction().commit();
        }

        if(townId.isEmpty())
        {
            System.out.println("No such town.");
        }
        else
        {
            if(deletedAddresses == 1)
            {
                System.out.printf("%d address in %s deleted%n", deletedAddresses, townName);
            }
            else
            {
                System.out.printf("%d addresses in %s deleted%n", deletedAddresses, townName);
            }
        }
    }
}


