package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "colors")
public class Color extends BaseEntity
{
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "hex", unique = true, nullable = false)
    private String hex;

    @OneToMany(mappedBy = "primaryKitColor")
    private Set<Team> primaryKitTeams;

    @OneToMany(mappedBy = "secondaryKitColor")
    private Set<Team> secondaryKitTeams;
}
