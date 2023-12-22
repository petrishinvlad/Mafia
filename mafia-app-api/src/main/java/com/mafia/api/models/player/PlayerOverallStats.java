package com.mafia.api.models.player;

import java.util.List;
import lombok.Data;

@Data
public class PlayerOverallStats extends PlayerSeasonStats {
    public PlayerOverallStats() {
        super();
        // this.gamesWonAsCivilian = 0;
        // this.gamesWonAsMafia = 0;
        // this.gamesWonAsSheriff = 0;
        // this.gamesWonAsGodfather = 0;
        // this.gamesLostAsCivilian = 0;
        // this.gamesLostAsMafia = 0;
        // this.gamesLostAsSheriff = 0;
        // this.gamesLostAsGodfather = 0;
        // this.firstKilled = 0;
        // this.mafiaRating = 0.0;
        // this.civilianRating = 0.0;
        // this.sheriffRating = 0.0;
        // this.godfatherRating = 0.0;
    }

    public PlayerOverallStats(final List<PlayerSeasonStats> seasonStats) {
        super();
        seasonStats.stream().forEach(seasonStat -> {
            // this.setGamesWonAsCivilian(this.getGamesWonAsCivilian() 
            //                             + seasonStat.getGamesWonAsCivilian());
            // this.setGamesWonAsMafia(this.getGamesWonAsMafia() 
            //                             + seasonStat.getGamesWonAsMafia());
            // this.setGamesWonAsSheriff(this.getGamesWonAsSheriff() 
            //                             + seasonStat.getGamesWonAsSheriff());
            // this.setGamesWonAsGodfather(this.getGamesWonAsGodfather() 
            //                             + seasonStat.getGamesWonAsGodfather());
            // this.setGamesLostAsCivilian(this.getGamesLostAsCivilian() 
            //                             + seasonStat.getGamesLostAsCivilian());
            // this.setGamesLostAsMafia(this.getGamesLostAsMafia() 
            //                             + seasonStat.getGamesLostAsMafia());
            // this.setGamesLostAsSheriff(this.getGamesLostAsSheriff() 
            //                             + seasonStat.getGamesLostAsSheriff());
            // this.setGamesLostAsGodfather(this.getGamesLostAsGodfather() 
            //                             + seasonStat.getGamesLostAsGodfather());
        });
    }
}