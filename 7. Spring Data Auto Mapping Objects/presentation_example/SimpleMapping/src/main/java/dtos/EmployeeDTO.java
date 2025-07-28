package dtos;

import entities.Employee;

public class EmployeeDTO
{
    private long id;
    private String firstName;
    private String addressCity;

    public EmployeeDTO() {}

    public EmployeeDTO(long id, String firstName, String addressCity)
    {
        this.id = id;
        this.firstName = firstName;
        this.addressCity = addressCity;
    }

    public EmployeeDTO(Employee employee)
    {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.addressCity = employee.getAddress().getCity();
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

    public String getAddressCity()
    {
        return addressCity;
    }

    public void setAddressCity(String addressCity)
    {
        this.addressCity = addressCity;
    }

    @Override
    public String toString()
    {
        return "EmployeeDTO {" + "id = " + id + ", firstName = '" + firstName + '\'' + ", addressCity = '" + addressCity + '\'' + '}';
    }
}
