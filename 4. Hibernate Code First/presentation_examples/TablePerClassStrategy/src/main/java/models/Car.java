package models;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car extends Vehicle
{
    private final static String type = "CAR";
    public Car()
    {
        super(type);
    }
}