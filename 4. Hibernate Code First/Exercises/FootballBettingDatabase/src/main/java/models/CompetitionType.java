package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "competition_types")
public class CompetitionType extends BaseEntity
{
    @Column(name = "type", unique = true, nullable = false)
    private String type;

    @OneToMany(mappedBy = "competitionType")
    private Set<Competition> competitions;
}
