package models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game extends BaseEntity
{
    @Column(name = "home_team_goals", nullable = false)
    private Integer homeTeamGoals;

    @Column(name = "away_team_goals", nullable = false)
    private Integer awayTeamGoals;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "home_team_win_bet_rate")
    private Float homeTeamWinBetRate;

    @Column(name = "away_team_win_bet_rate")
    private Float awayTeamWinBetRate;

    @Column(name = "draw_game_bet_rate")
    private Float drawGameBetRate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "home_team_id", referencedColumnName = "id", nullable = false)
    private Team homeTeam;

    @ManyToOne(optional = false)
    @JoinColumn(name = "away_team_id", referencedColumnName = "id", nullable = false)
    private Team awayTeam;

    @ManyToOne
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "round_id", referencedColumnName = "id")
    private Round round;

    @OneToMany(mappedBy = "game")
    private Set<PlayerStatistic> playerStatistics;

    @OneToMany(mappedBy = "game")
    private Set<BetGame> betGames;
}
