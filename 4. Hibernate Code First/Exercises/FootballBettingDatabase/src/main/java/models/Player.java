package models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "players")
public class Player extends BaseEntity
{
    @Column(name = "name", length = 1000, nullable = false)
    private String name;

    @Column(name = "squad_number", nullable = false)
    private Integer squadNumber;

    @Column(name = "is_injured")
    private Boolean isInjured;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @OneToMany(mappedBy = "player")
    private Set<PlayerStatistic> statistics;
}
