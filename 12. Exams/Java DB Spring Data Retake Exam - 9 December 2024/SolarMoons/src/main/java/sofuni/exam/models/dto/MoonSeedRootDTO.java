package sofuni.exam.models.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "moons")
@XmlAccessorType(XmlAccessType.FIELD)
public class MoonSeedRootDTO
{
    @XmlElement(name = "moon")
    private List<MoonSeedDTO> moonSeedDTOList;

    public List<MoonSeedDTO> getMoonSeedDTOList()
    {
        return moonSeedDTOList;
    }

    public MoonSeedRootDTO setMoonSeedDTOList(List<MoonSeedDTO> moonSeedDTOS)
    {
        this.moonSeedDTOList = moonSeedDTOS;
        return this;
    }
}