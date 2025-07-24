package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "continents")
public class Continent extends BaseEntity
{
    @Column(name = "name", length = 50, unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "continents")
    private Set<Country> countries = new HashSet<>();
}
