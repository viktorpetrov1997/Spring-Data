package models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity
{
    @Column(name = "name", length = 500, unique = true, nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "countries_continents", joinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "continent_id", referencedColumnName = "id"))
    private Set<Continent> continents = new HashSet<>();

    @OneToMany(mappedBy = "country")
    private Set<Town> towns;
}
