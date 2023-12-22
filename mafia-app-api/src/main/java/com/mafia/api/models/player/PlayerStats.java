package com.mafia.api.models.player;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerStats {
    private Player player;
    private List<PlayerSeasonStats> seasonStats;

    private PlayerOverallStats getOverallStats() {
        return new PlayerOverallStats(seasonStats);
    } 
}
