import entities.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AddressesWithEmployeeCount
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercises");

        EntityManager em = emf.createEntityManager();

        TypedQuery<Address> query = em.createQuery("SELECT a from Address AS a ORDER BY size(a.employees) DESC LIMIT 10", Address.class);

        List<Address> addresses = query.getResultList();

        for(Address address : addresses)
        {
            String townName = address.getTown() == null ? "n/a" : address.getTown().getName();
            System.out.printf("%s, %s - %d employees%n", address.getText(), townName, address.getEmployees().size());
        }
    }
}
