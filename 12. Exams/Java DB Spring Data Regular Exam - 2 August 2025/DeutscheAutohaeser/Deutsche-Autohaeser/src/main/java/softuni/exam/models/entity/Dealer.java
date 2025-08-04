package softuni.exam.models.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "dealers")
public class Dealer extends BaseEntity
{
    @Column(name = "average_monthly_turnover", nullable = false)
    private Double averageMonthlyTurnover;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "salary", nullable = false)
    private Double salary;

    @ManyToOne
    @JoinColumn(name = "offering_car_id")
    private Car offeringCar;

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

    public LocalDate getBirthday()
    {
        return birthday;
    }

    public void setBirthday(LocalDate birthday)
    {
        this.birthday = birthday;
    }

    public Car getOfferingCar()
    {
        return offeringCar;
    }

    public void setOfferingCar(Car offeringCar)
    {
        this.offeringCar = offeringCar;
    }
}
