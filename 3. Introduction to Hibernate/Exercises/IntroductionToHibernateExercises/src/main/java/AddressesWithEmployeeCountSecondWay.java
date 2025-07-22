import dtos.AddressSummaryDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AddressesWithEmployeeCountSecondWay
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercises");

        EntityManager em = emf.createEntityManager();

        TypedQuery<AddressSummaryDto> query = em.createQuery("SELECT a.text, a.town.name, size(a.employees) from Address AS a ORDER BY size(a.employees) DESC LIMIT 10", AddressSummaryDto.class);

        List<AddressSummaryDto> addresses = query.getResultList();

        for(AddressSummaryDto address : addresses)
        {
            String townName = address.getTownName() == null ? "n/a" : address.getTownName();
            System.out.printf("%s, %s - %d employees%n", address.getText(), townName, address.getEmployeesCount());
        }
    }
}
