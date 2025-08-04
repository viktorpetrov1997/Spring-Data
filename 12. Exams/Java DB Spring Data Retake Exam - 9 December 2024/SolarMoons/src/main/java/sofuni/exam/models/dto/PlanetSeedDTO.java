package sofuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import sofuni.exam.models.enums.Type;

public class PlanetSeedDTO
{
    @Expose
    @NotNull
    @Positive
    private int diameter;

    @Expose
    @NotNull
    @Positive
    private Long distanceFromSun;

    @Expose
    @NotNull
    @Length(min = 3, max = 20)
    private String name;

    @Expose
    @NotNull
    @Positive
    private double orbitalPeriod;

    @Expose
    @NotNull
    private Type type;

    public int getDiameter()
    {
        return diameter;
    }

    public void setDiameter(int diameter)
    {
        this.diameter = diameter;
    }

    public Long getDistanceFromSun()
    {
        return distanceFromSun;
    }

    public void setDistanceFromSun(Long distanceFromSun)
    {
        this.distanceFromSun = distanceFromSun;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getOrbitalPeriod()
    {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(double orbitalPeriod)
    {
        this.orbitalPeriod = orbitalPeriod;
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }
}
