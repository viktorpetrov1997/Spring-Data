package models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredients")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class BasicIngredient
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Many-to-Many with Shampoos
    @ManyToMany(mappedBy = "ingredients", targetEntity = BasicShampoo.class)
    private Set<BasicShampoo> shampoos = new HashSet<>();

    public BasicIngredient(Long id, String name, Set<BasicShampoo> shampoos)
    {
        this.id = id;
        this.name = name;
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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