package com.mafia.api.client.polemica.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.mafia.api.client.polemica.models.PolemicaGameStats;
import com.mafia.api.client.polemica.models.PolemicaPlayer;
import com.mafia.api.models.GameParticipant;
import com.mafia.api.models.GameTable;
import com.mafia.api.repository.GameRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PolemicaService {
    private final GameRepository gameRepository;

    public GameTable getGameResults(final PolemicaGameStats gameStats) {
        Set<GameParticipant> gameParticipants = new HashSet<>();
        List<PolemicaPlayer> players = gameStats.getPlayers();
        for (PolemicaPlayer player: players) {
            gameParticipants.add(getPlayer(player));
        }
        GameTable gameTable = GameTable.builder()
            .bestMove(null)
            .gameTime(null)
            .judge(null)
            .judgeComments(null)
            .playerComments(null)
            .gameParticipants(gameParticipants)
        .build();

        return gameTable;
    }

    private GameParticipant getPlayer(final PolemicaPlayer polemicaPlayer) {
        return GameParticipant.builder().build();
    }

    public GameTable saveGameResult(final PolemicaGameStats gameStats) {
        GameTable gameResult = getGameResults(gameStats);
        gameRepository.save(gameResult);
        return gameResult;
    }
}
