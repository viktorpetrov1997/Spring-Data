package SpringMapping;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class DBEmployee
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private BigDecimal salary;

    @Column
    private LocalDate birthday;

    @Column
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    private DBEmployee manager;

    public DBEmployee() {}

    public DBEmployee(String firstName, String lastName, BigDecimal salary, LocalDate birthday, String address, DBEmployee manager)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.birthday = birthday;
        this.address = address;
        this.manager = manager;
    }

    public DBEmployee(String firstName, String lastName, BigDecimal salary, DBEmployee manager)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.manager = manager;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

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

    public BigDecimal getSalary()
    {
        return salary;
    }

    public void setSalary(BigDecimal salary)
    {
        this.salary = salary;
    }

    public LocalDate getBirthday()
    {
        return birthday;
    }

    public void setBirthday(LocalDate birthday)
    {
        this.birthday = birthday;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public DBEmployee getManager()
    {
        return manager;
    }

    public void setManager(DBEmployee manager)
    {
        this.manager = manager;
    }
}
