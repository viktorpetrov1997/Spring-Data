package models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "competitions")
public class Competition extends BaseEntity
{
    @Column(name = "name", length = 500, nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "competition_type_id", referencedColumnName = "id", nullable = false)
    private CompetitionType competitionType;

    @OneToMany(mappedBy = "competition")
    private Set<Game> games;
}
