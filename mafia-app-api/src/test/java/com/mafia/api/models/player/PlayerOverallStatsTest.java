package com.mafia.api.models.player;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.mafia.api.models.player.PlayerOverallStats;
import com.mafia.api.models.player.PlayerSeasonStats;

public class PlayerOverallStatsTest {
    @Test
    void testOverallStatsSummarizesAllRequiredFields() {
        final List<PlayerSeasonStats> seasonStats = List.of(
            new PlayerSeasonStats().builder()
                .gamesWonAsCivilian(1)
                .gamesWonAsMafia(2)
                .gamesWonAsSheriff(3)
                .gamesWonAsGodfather(4)
                .gamesLostAsCivilian(5)
                .gamesLostAsMafia(6)
                .gamesLostAsSheriff(7)
                .gamesLostAsGodfather(8)
                .build(),

            new PlayerSeasonStats().builder()
                .gamesWonAsCivilian(9)
                .gamesWonAsMafia(10)
                .gamesWonAsSheriff(11)
                .gamesWonAsGodfather(12)
                .gamesLostAsCivilian(13)
                .gamesLostAsMafia(14)
                .gamesLostAsSheriff(15)
                .gamesLostAsGodfather(16)
                .build(),

            new PlayerSeasonStats().builder()
                .gamesWonAsCivilian(17)
                .gamesWonAsMafia(18)
                .gamesWonAsSheriff(19)
                .gamesWonAsGodfather(20)
                .gamesLostAsCivilian(21)
                .gamesLostAsMafia(22)
                .gamesLostAsSheriff(23)
                .gamesLostAsGodfather(24)
                .build()
        );

        final PlayerOverallStats overallStats = new PlayerOverallStats(seasonStats);

        assertAll("Wrong player overall stats calculation",
            () -> assertEquals(27, overallStats.getGamesWonAsCivilian()),
            () -> assertEquals(30, overallStats.getGamesWonAsMafia()),
            () -> assertEquals(33, overallStats.getGamesWonAsSheriff()),
            () -> assertEquals(36, overallStats.getGamesWonAsGodfather()),
            () -> assertEquals(39, overallStats.getGamesLostAsCivilian()),
            () -> assertEquals(42, overallStats.getGamesLostAsMafia()),
            () -> assertEquals(45, overallStats.getGamesLostAsSheriff()),
            () -> assertEquals(48, overallStats.getGamesLostAsGodfather())
        );
    }
}
