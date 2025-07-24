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

        Car car1 = new Car("CAR", 4);
        Car car2 = new Car("CAR", 5);

        Truck truck1 = new Truck("TRUCK", 2, 10000);
        Truck truck2 = new Truck("TRUCK", 3, 12000);

        em.persist(car1);
        em.persist(car2);
        em.persist(truck1);
        em.persist(truck2);

        em.getTransaction().commit();
    }
}