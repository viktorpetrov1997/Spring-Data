package softuni.exam.models.dto;

import jakarta.validation.constraints.Positive;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Length;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DealerSeedDTO
{
    @XmlElement(name = "average_monthly_turnover")
    @Positive
    private Double averageMonthlyTurnover;

    @XmlElement(name = "birthday")
    private String birthday;

    @XmlElement(name = "first_name")
    @Length(min = 2, max = 30)
    private String firstName;

    @XmlElement(name = "last_name")
    @Length(min = 2, max = 30)
    private String lastName;

    @XmlElement(name = "salary")
    @Positive
    private Double salary;

    @XmlElement(name = "offering_car_id")
    private Long offeringCar;

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Double getSalary()
    {
        return salary;
    }

    public void setSalary(Double salary)
    {
        this.salary = salary;
    }

    public Double getAverageMonthlyTurnover()
    {
        return averageMonthlyTurnover;
    }

    public void setAverageMonthlyTurnover(Double averageMonthlyTurnover)
    {
        this.averageMonthlyTurnover = averageMonthlyTurnover;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    public Long getOfferingCar()
    {
        return offeringCar;
    }

    public void setOfferingCar(Long offeringCar)
    {
        this.offeringCar = offeringCar;
    }
}
