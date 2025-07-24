package models;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PassengerVehicle extends Vehicle
{
    private int noOfpassengers;

    public PassengerVehicle() {}

    public PassengerVehicle(String type, int noOfpassengers)
    {
        super(type);
        this.noOfpassengers = noOfpassengers;
    }

    public int getNoOfpassengers()
    {
        return noOfpassengers;
    }

    public void setNoOfpassengers(int noOfpassengers)
    {
        this.noOfpassengers = noOfpassengers;
    }
}