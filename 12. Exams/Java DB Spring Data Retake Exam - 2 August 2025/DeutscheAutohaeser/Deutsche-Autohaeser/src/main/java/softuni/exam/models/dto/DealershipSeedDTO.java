package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class DealershipSeedDTO
{
    @Expose
    @NotNull
    @Length(min = 3, max = 30)
    private String name;

    @Expose
    @NotNull
    @Length(min = 5)
    private String description;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
