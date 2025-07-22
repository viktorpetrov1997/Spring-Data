package dtos;

import java.math.BigDecimal;

public class DepartmentSummaryDto
{
    private String name;
    private BigDecimal maxSalary;

    public DepartmentSummaryDto(String name, BigDecimal maxSalary)
    {
        this.name = name;
        this.maxSalary = maxSalary;
    }

    public String getName()
    {
        return name;
    }
    public BigDecimal getMaxSalary()
    {
        return maxSalary;
    }
}
