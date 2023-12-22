package com.mafia.api.models.player;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// @Entity
// @Table(name = "PLAYER_SEASON_STATS")
public class PlayerSeasonStats {
    // @Id
    // @GeneratedValue
    // @Column(name = "ID")
    protected int id;

    // @Column(name = "CIVILIAN_WON")
    // protected int gamesWonAsCivilian;
    
    // @Column(name = "MAFIA_WON")
    // protected int gamesWonAsMafia;
    
    // @Column(name = "SHERIFF_WON")
    // protected int gamesWonAsSheriff;

    // @Column(name = "GODFATHER_WON")
    // protected int gamesWonAsGodfather;

    // @Column(name = "CIVILIAN_LOST")
    // protected int gamesLostAsCivilian;

    // @Column(name = "MAFIA_LOST")
    // protected int gamesLostAsMafia;

    // @Column(name = "SHERIFF_LOST")
    // protected int gamesLostAsSheriff;

    // @Column(name = "GODFATHER_LOST")
    // protected int gamesLostAsGodfather;

    // @Column(name = "KILLED_FIRST")
    // protected int firstKilled;

    // @Column(name = "MAFIA_RATING")
    // protected double mafiaRating;

    // @Column(name = "CIVILIAN_RATING")
    // protected double civilianRating;

    // @Column(name = "SHERIFF_RATING")
    // protected double sheriffRating;

    // @Column(name = "GODFATHER_RATING")
    // protected double godfatherRating;

    // public int getGamesPlayed() {
    //     return this.gamesWonAsCivilian + this.gamesWonAsMafia 
    //         + this.gamesWonAsSheriff 
    //         + this.gamesWonAsGodfather 
    //         + this.gamesLostAsCivilian 
    //         + this.gamesLostAsMafia 
    //         + this.gamesLostAsSheriff 
    //         + this.gamesLostAsGodfather;
    // }
}
