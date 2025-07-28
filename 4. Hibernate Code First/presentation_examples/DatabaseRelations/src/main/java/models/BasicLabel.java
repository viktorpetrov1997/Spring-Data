package models;

import jakarta.persistence.*;

@Entity
@Table(name = "labels")
public class BasicLabel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // One-to-One Bidirectional with Shampoo
    @OneToOne(mappedBy = "label", targetEntity = BasicShampoo.class)
    private BasicShampoo shampoo;

    public BasicLabel() {}

    public BasicLabel(Long id, String name, BasicShampoo shampoo)
    {
        this.id = id;
        this.name = name;
        this.shampoo = shampoo;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public BasicShampoo getShampoo()
    {
        return shampoo;
    }

    public void setShampoo(BasicShampoo shampoo)
    {
        this.shampoo = shampoo;
    }
}
