package bg.softuni;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.*;

public class Main
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("labs");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Bike bike = new Bike();
        bike.setModel("BMX");

        em.persist(bike);

        Car car = new Car();
        car.setModel("BMW");
        car.setSeats(4);

        em.persist(car);

        Plane plane = new Plane();
        plane.setModel("Boeing 747");

        em.persist(plane);

        Truck truck = new Truck();
        truck.setModel("Volvo 140");
        truck.setLoadCapacity(2.5);

        em.persist(truck);

        em.getTransaction().commit();
    }
}