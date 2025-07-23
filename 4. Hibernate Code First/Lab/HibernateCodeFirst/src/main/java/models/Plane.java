package models;

import jakarta.persistence.*;

@Entity
@Table(name = "planes")
public class Plane extends Vehicle
{
    @Basic
    private int passengersCapacity;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    public int getPassengersCapacity()
    {
        return this.passengersCapacity;
    }

    public void setPassengersCapacity(int passengersCapacity)
    {
        this.passengersCapacity = passengersCapacity;
    }

    public Company getCompany()
    {
        return this.company;
    }

    public void setCompany(Company company)
    {
        this.company = company;
    }
}
