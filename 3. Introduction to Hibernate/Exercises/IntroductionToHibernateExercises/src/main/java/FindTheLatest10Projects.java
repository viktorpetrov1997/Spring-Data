import entities.Project;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class FindTheLatest10Projects
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercises");

        EntityManager em = emf.createEntityManager();

        TypedQuery<Project> query = em.createQuery("SELECT p FROM Project AS p ORDER BY p.startDate DESC", Project.class)
                .setMaxResults(10);

        List<Project> projects = query.getResultStream().sorted(Comparator.comparing(Project::getName)).toList();

        for(Project project : projects)
        {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            System.out.printf("Project name: %s%n", project.getName());
            System.out.printf("    Project Description: %s%n", project.getDescription());
            System.out.printf("    Project Start Date:%s%n", project.getStartDate().format(dateTimeFormatter));
            System.out.printf("    Project End Date: %s%n", project.getEndDate());
        }
    }
}
