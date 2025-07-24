package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "rounds")
public class Round extends BaseEntity
{
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @OneToMany(mappedBy = "round")
    private Set<Game> games;
}
