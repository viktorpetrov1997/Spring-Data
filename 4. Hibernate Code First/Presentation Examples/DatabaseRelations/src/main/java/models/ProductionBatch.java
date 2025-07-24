package models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "batches")
public class ProductionBatch
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    // One-to-Many with Shampoos
    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BasicShampoo> shampoos = new HashSet<>();

    public ProductionBatch() {}

    public ProductionBatch(Long id, String code, Set<BasicShampoo> shampoos)
    {
        this.id = id;
        this.code = code;
        this.shampoos = shampoos;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public Set<BasicShampoo> getShampoos()
    {
        return shampoos;
    }

    public void setShampoos(Set<BasicShampoo> shampoos)
    {
        this.shampoos = shampoos;
    }
}