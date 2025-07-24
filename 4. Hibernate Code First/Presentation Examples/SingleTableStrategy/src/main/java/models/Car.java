package models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "car")
public class Car extends PassengerVehicle
{
    private final static String type = "CAR";

    public Car() {}

    public Car(String type, int noOfpassengers)
    {
        super(type, noOfpassengers);
    }
}