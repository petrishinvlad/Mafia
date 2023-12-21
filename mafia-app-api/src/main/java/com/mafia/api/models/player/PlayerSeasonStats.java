package com.mafia.api.models.player;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerSeasonStats {
    public int getGamesPlayed() {
        return this.gamesWonAsCivilian + this.gamesWonAsMafia 
            + this.gamesWonAsSheriff 
            + this.gamesWonAsGodfather 
            + this.gamesLostAsCivilian 
            + this.gamesLostAsMafia 
            + this.gamesLostAsSheriff 
            + this.gamesLostAsGodfather;
    }
    int gamesWonAsCivilian;
    int gamesWonAsMafia;
    int gamesWonAsSheriff;
    int gamesWonAsGodfather;
    int gamesLostAsCivilian;
    int gamesLostAsMafia;
    int gamesLostAsSheriff;
    int gamesLostAsGodfather;
    int firstKilled;
    double mafiaRating;
    double civilianRating;
    double sheriffRating;
    double godfatherRating;
}
