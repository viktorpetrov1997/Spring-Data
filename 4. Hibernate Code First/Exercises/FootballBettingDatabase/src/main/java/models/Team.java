package models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity
{
    @Column(name = "name", length = 500, nullable = false)
    private String name;

    @Column(name = "logo", length = 1000)
    private String logo;

    @Column(name = "initials", length = 5)
    private String initials;

    @Column(name = "budget")
    private BigDecimal budget;

    @ManyToOne
    @JoinColumn(name = "town_id", nullable = false)
    private Town town;

    @ManyToOne
    @JoinColumn(name = "primary_kit_color_id", nullable = false)
    private Color primaryKitColor;

    @ManyToOne
    @JoinColumn(name = "secondary_kit_color_id", nullable = false)
    private Color secondaryKitColor;

    @OneToMany(mappedBy = "team")
    private Set<Player> players;

    @OneToMany(mappedBy = "homeTeam")
    private Set<Game> homeGames;

    @OneToMany(mappedBy = "awayTeam")
    private Set<Game> awayGames;
}
