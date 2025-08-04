package softuni.exam.models.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "dealers")
@XmlAccessorType(XmlAccessType.FIELD)
public class DealerSeedRootDTO
{
    @XmlElement(name = "dealer")
    private List<DealerSeedDTO> dealerSeedDTOS;

    public List<DealerSeedDTO> getDealerSeedDTOS()
    {
        return dealerSeedDTOS;
    }

    public void setDealerSeedDTOS(List<DealerSeedDTO> dealerSeedDTOS)
    {
        this.dealerSeedDTOS = dealerSeedDTOS;
    }
}
