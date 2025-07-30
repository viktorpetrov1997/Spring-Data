package SpringMapping.entities;

import java.math.BigDecimal;
import java.util.List;

public class Employee
{
    private long id;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private Address address;
    private boolean isOnHoliday;
    private Employee manager;
    private List<Employee> team;

    // FIXME: LocalDate
    private String birthday;

    public Employee() {}

    public Employee(String firstName, String lastName, BigDecimal salary, Address address)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.address = address;
    }

    public Employee(String firstName, String lastName, BigDecimal salary, Address address, boolean isOnHoliday, Employee manager, List<Employee> team, String birthday)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.address = address;
        this.isOnHoliday = isOnHoliday;
        this.manager = manager;
        this.team = team;
        this.birthday = birthday;
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

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public boolean isOnHoliday()
    {
        return isOnHoliday;
    }

    public void setOnHoliday(boolean onHoliday)
    {
        isOnHoliday = onHoliday;
    }

    public Employee getManager()
    {
        return manager;
    }

    public void setManager(Employee manager)
    {
        this.manager = manager;
    }

    public List<Employee> getTeam()
    {
        return team;
    }

    public void setTeam(List<Employee> team)
    {
        this.team = team;
    }
}
