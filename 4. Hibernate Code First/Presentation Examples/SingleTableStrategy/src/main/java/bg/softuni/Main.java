package bg.softuni;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.Car;
import models.Truck;

public class Main
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("labs");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Car car = new Car("CAR", 5);
        Truck truck = new Truck("TRUCK", 10000, 2);

        em.persist(car);
        em.persist(truck);

        em.getTransaction().commit();
    }
}