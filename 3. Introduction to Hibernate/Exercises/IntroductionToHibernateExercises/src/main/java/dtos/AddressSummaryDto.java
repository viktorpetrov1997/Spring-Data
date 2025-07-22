package dtos;

public class AddressSummaryDto
{
    private final String text;
    private final String townName;
    private final Integer employeesCount;

    public AddressSummaryDto(String text, String townName, Integer employeesCount)
    {
        this.text = text;
        this.townName = townName;
        this.employeesCount = employeesCount;
    }

    public String getText()
    {
        return text;
    }

    public String getTownName()
    {
        return townName;
    }

    public Integer getEmployeesCount()
    {
        return employeesCount;
    }
}
