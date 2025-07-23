package models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "trucks")
public class Truck extends Vehicle
{
    @Basic
    private double loadCapacity;

    @ManyToMany
    @JoinTable(name = "drivers_trucks", joinColumns = @JoinColumn(name = "truck_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id", referencedColumnName = "id"))
    private Set<Driver> drivers = new HashSet<>();

    public double getLoadCapacity()
    {
        return this.loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity)
    {
        this.loadCapacity = loadCapacity;
    }

    public Set<Driver> getDrivers()
    {
        return this.drivers;
    }

    public void setDrivers(Set<Driver> drivers)
    {
        this.drivers = drivers;
    }
}
