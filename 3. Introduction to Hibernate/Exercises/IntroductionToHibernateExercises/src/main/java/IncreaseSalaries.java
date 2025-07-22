import entities.Employee;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IncreaseSalaries
{
    public static void main(String[] args)
    {
        Set<String> departments = new HashSet<>();
        departments.add("Engineering");
        departments.add("Tool Design");
        departments.add("Marketing");
        departments.add("Information Services");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercises");

        EntityManager em = emf.createEntityManager();

        TypedQuery<Integer> departmentIdsQuery = em.createQuery("SELECT d.id FROM Department AS d WHERE d.name IN (:departments)", Integer.class)
                .setParameter("departments", departments);
        List<Integer> departmentIds = departmentIdsQuery.getResultList();

        TypedQuery<Integer> employeeIdsQuery = em.createQuery("SELECT e.id FROM Employee AS e WHERE e.department.id IN (:ids)", Integer.class)
                .setParameter("ids", departmentIds);
        List<Integer> employeeIds = employeeIdsQuery.getResultList();

        em.getTransaction().begin();

        Query updateQuery = em.createQuery("UPDATE Employee AS e SET e.salary = e.salary * 1.12 where e.id IN (:ids)")
                .setParameter("ids", employeeIds);
        updateQuery.executeUpdate();

        TypedQuery<Employee> resultQuery = em.createQuery("SELECT e FROM Employee AS e WHERE e.id IN (:ids)", Employee.class)
                .setParameter("ids", employeeIds);
        List<Employee> employeesWithIncreasedSalaries = resultQuery.getResultList();

        em.getTransaction().commit();

        for(Employee employee : employeesWithIncreasedSalaries)
        {
            System.out.printf("%s %s ($%.2f)%n", employee.getFirstName(), employee.getLastName(), employee.getSalary());
        }
    }
}
