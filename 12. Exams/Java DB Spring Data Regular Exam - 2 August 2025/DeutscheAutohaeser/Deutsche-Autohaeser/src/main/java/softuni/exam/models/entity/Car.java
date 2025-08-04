package softuni.exam.models.entity;

import jakarta.persistence.*;
import java.util.List;

import softuni.exam.models.entity.CarType;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity
{
    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "vin", nullable = false, unique = true)
    private String vin;

    @Column(name = "mileage", nullable = false)
    private Integer mileage;

    @Enumerated(EnumType.STRING)
    @Column(name = "car_type", nullable = false)
    private CarType carType;

    @ManyToOne
    @JoinColumn(name = "dealership_id")
    private Dealership dealership;

    @OneToMany(mappedBy = "offeringCar")
    private List<Dealer> dealersOffering;

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getVin()
    {
        return vin;
    }

    public void setVin(String vin)
    {
        this.vin = vin;
    }

    public Integer getMileage()
    {
        return mileage;
    }

    public void setMileage(Integer mileage)
    {
        this.mileage = mileage;
    }

    public CarType getCarType()
    {
        return carType;
    }

    public void setCarType(CarType carType)
    {
        this.carType = carType;
    }

    public Dealership getDealership()
    {
        return dealership;
    }

    public void setDealership(Dealership dealership)
    {
        this.dealership = dealership;
    }

    public List<Dealer> getDealersOffering()
    {
        return dealersOffering;
    }

    public void setDealersOffering(List<Dealer> dealersOffering)
    {
        this.dealersOffering = dealersOffering;
    }
}
