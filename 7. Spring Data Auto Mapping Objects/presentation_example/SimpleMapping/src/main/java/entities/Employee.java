package entities;

import java.math.BigDecimal;

public class Employee
{
    private long id;
    private String firstName;
    private BigDecimal salary;
    private Address address;

    public Employee() {}

    public Employee(String firstName, BigDecimal salary, Address address)
    {
        this.firstName = firstName;
        this.salary = salary;
        this.address = address;
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
}
