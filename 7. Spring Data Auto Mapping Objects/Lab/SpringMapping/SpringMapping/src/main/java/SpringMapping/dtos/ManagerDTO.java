package SpringMapping.dtos;

import java.util.List;
import java.util.stream.Collectors;

public class ManagerDTO
{
    private String firstName;
    private String lastName;
    private List<EmployeeDTO> team;

    public ManagerDTO() {}

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

    public List<EmployeeDTO> getTeam()
    {
        return team;
    }

    public void setTeam(List<EmployeeDTO> team)
    {
        this.team = team;
    }

    @Override
    public String toString()
    {
        String teamStr = this.team.stream().map(e -> String.format("- %s %s %.2f", e.getFirstName(),
                e.getLastName(), e.getSalary())).collect(Collectors.joining("\n"));

        return String.format("%s %s | Employees : %d%n%s", firstName, lastName, this.team.size(), teamStr);
    }
}
