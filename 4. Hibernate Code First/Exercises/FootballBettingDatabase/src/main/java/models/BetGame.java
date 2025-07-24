package models;

import jakarta.persistence.*;

@Entity
@Table(name = "bet_games")
public class BetGame extends BaseEntity
{
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "bet_id")
    private Bet bet;

    @ManyToOne
    @JoinColumn(name = "result_prediction")
    private ResultPrediction resultPrediction;
}
