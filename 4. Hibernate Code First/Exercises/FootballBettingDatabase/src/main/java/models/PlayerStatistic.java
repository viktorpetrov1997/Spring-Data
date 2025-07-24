package models;

import jakarta.persistence.*;

@Entity
@Table(name = "player_statistics")
public class PlayerStatistic extends BaseEntity
{
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(name = "scored_goals")
    private int scoredGoals;

    @Column(name = "player_assists")
    private int playerAssists;

    @Column(name = "played_minutes")
    private int playedMinutes;
}
