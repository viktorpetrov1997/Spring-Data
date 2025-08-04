package softuni.exam.models.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity
{
    @Column(name = "discounted")
    private Boolean discounted;

    @Column(name = "number", unique = true, nullable = false)
    private String number;

    @Column(name = "sale_date", nullable = false)
    private LocalDateTime saleDate;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @OneToMany(mappedBy = "sale")
    private List<Device> devices;

    public Boolean getDiscounted()
    {
        return discounted;
    }

    public void setDiscounted(Boolean discounted)
    {
        this.discounted = discounted;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public LocalDateTime getSaleDate()
    {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate)
    {
        this.saleDate = saleDate;
    }

    public Seller getSeller()
    {
        return seller;
    }

    public void setSeller(Seller seller)
    {
        this.seller = seller;
    }

    public List<Device> getDevices()
    {
        return devices;
    }

    public void setDevices(List<Device> devices)
    {
        this.devices = devices;
    }
}
