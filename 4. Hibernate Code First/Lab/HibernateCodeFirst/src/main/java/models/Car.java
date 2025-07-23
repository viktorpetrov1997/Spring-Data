package models;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car extends Vehicle
{
    @Basic
    private Integer seats;

    @OneToOne
    @JoinColumn(name = "plate_number_id", referencedColumnName = "id")
    private PlateNumber plateNumber;

    public Integer getSeats()
    {
        return this.seats;
    }

    public void setSeats(Integer seats)
    {
        this.seats = seats;
    }

    public PlateNumber getPlateNumber()
    {
        return this.plateNumber;
    }

    public void setPlateNumber(PlateNumber plateNumber)
    {
        this.plateNumber = plateNumber;
    }
}