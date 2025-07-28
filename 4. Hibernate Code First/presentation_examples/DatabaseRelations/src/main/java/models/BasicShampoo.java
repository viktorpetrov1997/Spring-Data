package models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shampoos")
public class BasicShampoo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    // One-to-One with Label
    @OneToOne(optional = false)
    @JoinColumn(name = "label_id", referencedColumnName = "id")
    private BasicLabel label;

    // Many-to-One with ProductionBatch
    @ManyToOne(optional = false)
    @JoinColumn(name = "batch_id", referencedColumnName = "id")
    private ProductionBatch batch;

    // Many-to-Many with Ingredients
    @ManyToMany
    @JoinTable(name = "shampoos_ingredients",
            joinColumns = @JoinColumn(name = "shampoo_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<BasicIngredient> ingredients = new HashSet<>();

    public BasicShampoo() {}

    public BasicShampoo(Long id, String brand, BasicLabel label, ProductionBatch batch, Set<BasicIngredient> ingredients)
    {
        this.id = id;
        this.brand = brand;
        this.label = label;
        this.batch = batch;
        this.ingredients = ingredients;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public BasicLabel getLabel()
    {
        return label;
    }

    public void setLabel(BasicLabel label)
    {
        this.label = label;
    }

    public ProductionBatch getBatch()
    {
        return batch;
    }

    public void setBatch(ProductionBatch batch)
    {
        this.batch = batch;
    }

    public Set<BasicIngredient> getIngredients()
    {
        return ingredients;
    }

    public void setIngredients(Set<BasicIngredient> ingredients)
    {
        this.ingredients = ingredients;
    }
}