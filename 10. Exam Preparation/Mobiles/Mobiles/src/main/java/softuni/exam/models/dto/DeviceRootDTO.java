package softuni.exam.models.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "devices")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceRootDTO
{
    @XmlElement(name = "device")
    private List<DeviceDTO> deviceDTOS;

    public List<DeviceDTO> getDeviceDTOS()
    {
        return deviceDTOS;
    }

    public void setDeviceDTOS(List<DeviceDTO> deviceDTOS)
    {
        this.deviceDTOS = deviceDTOS;
    }
}