package models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "truck")
public class Truck extends TransportationVehicle
{
    private final static String type = "TRUCK";

    private int noOfContainers;

    public Truck() {}

    public Truck(String type, int loadCapacity, int noOfContainers)
    {
        super(type, loadCapacity);
        this.noOfContainers = noOfContainers;
    }

    public int getNoOfContainers()
    {
        return noOfContainers;
    }

    public void setNoOfContainers(int noOfContainers)
    {
        this.noOfContainers = noOfContainers;
    }
}