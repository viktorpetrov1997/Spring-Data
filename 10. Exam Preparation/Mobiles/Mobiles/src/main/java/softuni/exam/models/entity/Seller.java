package softuni.exam.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "sellers")
public class Seller extends BaseEntity
{
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", unique = true, nullable = false)
    private String lastName;

    @Column(name = "personal_number", unique = true, nullable = false)
    private String personalNumber;

    @OneToMany(mappedBy = "seller")
    private List<Sale> sales;

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

    public String getPersonalNumber()
    {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber)
    {
        this.personalNumber = personalNumber;
    }

    public List<Sale> getSales()
    {
        return sales;
    }

    public void setSales(List<Sale> sales)
    {
        this.sales = sales;
    }
}
