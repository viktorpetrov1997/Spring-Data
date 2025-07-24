package models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity
{
    @Column(name = "name", length = 500, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "town")
    private Set<Team> teams;
}
