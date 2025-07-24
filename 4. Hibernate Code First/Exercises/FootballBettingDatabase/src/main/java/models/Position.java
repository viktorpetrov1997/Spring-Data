package models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "positions")
public class Position extends BaseEntity
{
    @Column(name = "description", length = 50, nullable = false)
    private String description;

    @OneToMany(mappedBy = "position")
    private Set<Player> players;
}
