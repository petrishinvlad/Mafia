package com.mafia.api.models.player;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerStats {
    public List<PlayerSeasonStats> seasonStats;

    public PlayerOverallStats getOverallStats() {
        return new PlayerOverallStats(seasonStats);
    }
}
