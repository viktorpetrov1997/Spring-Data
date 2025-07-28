package bg.softuni;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.Bike;
import models.Car;
import models.Vehicle;

public class Main
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("labs");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Vehicle bike = new Bike();
        Vehicle car = new Car();

        em.persist(bike);
        em.persist(car);

        em.getTransaction().commit();
    }
}