package bg.softuni;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.BasicLabel;
import models.BasicShampoo;
import models.ProductionBatch;

public class Main
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("labs");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        BasicLabel label = new BasicLabel();
        label.setName("Fresh");

        em.persist(label);

        ProductionBatch batch = new ProductionBatch();
        batch.setCode("Batch-001");

        em.persist(batch);

        BasicShampoo shampoo = new BasicShampoo();
        shampoo.setBrand("CoolMint");
        shampoo.setLabel(label);
        shampoo.setBatch(batch);

        em.persist(shampoo);

        em.getTransaction().commit();
    }
}