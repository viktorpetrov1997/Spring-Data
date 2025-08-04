package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import softuni.exam.models.entity.CarType;

public class CarSeedDTO
{
    @Expose
    @Length(min = 2, max = 30)
    private String brand;

    @Expose
    @Length(min = 2, max = 30)
    private String model;

    @Expose
    @SerializedName("VIN")
    @Length(min = 17, max = 17)
    private String vin;

    @Expose
    @Positive
    private Integer mileage;

    @Expose
    private CarType carType;

    @Expose
    private Long dealership;

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

    public Long getDealership()
    {
        return dealership;
    }

    public void setDealership(Long dealership)
    {
        this.dealership = dealership;
    }
}
