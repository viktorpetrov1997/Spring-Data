package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "result_predictions")
public class ResultPrediction extends BaseEntity
{
    @Column(name = "prediction")
    private String prediction;

    @OneToMany(mappedBy = "resultPrediction")
    private Set<BetGame> betGames;
}
