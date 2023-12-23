package com.mafia.api.models.player;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlayerStats {
    private Player player;
    private List<PlayerSeasonStats> seasonStats;

    private PlayerOverallStats getOverallStats() {
        return new PlayerOverallStats(seasonStats);
    } 
}
