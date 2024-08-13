package com.mafia.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.mafia.api.client.messenger.telegram.TelegramClientProvider;
import com.mafia.api.models.GameParticipant;
import com.mafia.api.models.GameTable;
import com.mafia.api.models.PlayerRole;
import com.mafia.api.models.mappers.GameParticipantListMapper;
import com.mafia.api.models.mappers.GameParticipantMapper;
import com.mafia.api.models.mappers.GameTableMapper;
import com.mafia.api.models.player.Player;
import com.mafia.api.models.requests.GameBestMoveRequest;
import com.mafia.api.models.requests.GameRequest;
import com.mafia.api.models.requests.NewGamePollRequest;
import com.mafia.api.models.requests.NewGameRequest;
import com.mafia.api.repository.GameParticipantRepository;
import com.mafia.api.repository.GameRepository;
import com.mafia.api.repository.PlayerRepository;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Named
public class GameService {
    private final TelegramClientProvider telegramClient;
    private final GameRepository gameRepository; 
    private final GameParticipantRepository gameParticipantRepository;
    private final PlayerRepository playerRepository;

    // private final GameTableMapper gameTableMapper;
    private final GameParticipantListMapper gameParticipantListMapper;

    public GameTable createNewGame(final NewGameRequest newGameRequest) {
        // GameTable game = gameTableMapper.newGameRequestToGameTable(newGameRequest);
        // gameRepository.save(game);

        // List<GameParticipant> gameParticipants = gameParticipantListMapper.newGameRequestToGameParticipants(newGameRequest, game);
        // gameParticipantRepository.saveAll(gameParticipants);
        
        // return game;
        return new GameTable();
    }

    public void setBestMove(final GameBestMoveRequest bestGameMoveRequest) {
        // validateSuspectedPlayers(bestGameMoveRequest.getSuspectedPlayers());
        // final GameTable gameTable = gameRepository.findById(bestGameMoveRequest.getGameId()).get();
        // Double bestMovePoints = getBestMovePoints(bestGameMoveRequest, gameTable);
        // for (GameParticipant gameParticipant: gameTable.getGameParticipants()) {
        //     if (gameParticipant.getPosition() == bestGameMoveRequest.getPlayerPosition()) {
        //         //Hibernate should make an automatic update
        //         gameParticipant.setPoints(gameParticipant.getPoints() + bestMovePoints);
        //     }
        // }
    }

    public GameTable finishGame(final GameRequest gameRequest) {
        GameTable gameTable = gameRepository.findById(gameRequest.getTableId()).get();
        // gameTable.setPlayerComments(gameRequest.getPlayerComments());
        // gameTable.setJudgeComments(gameRequest.getJudgeComments());
        // gameTable.setBestMove(null);
        return gameTable;
    }

    private void validateSuspectedPlayers(List<Integer> suspectedPlayers) {
        if (suspectedPlayers != null && suspectedPlayers.size() > 3) {
            throw new IllegalArgumentException("Impossible best move - too many players selected");
        }
    }

    private Double getBestMovePoints(final GameBestMoveRequest bestGameMoveRequest, final GameTable gameTable) {
        Double result = 0.0;
        // Set<GameParticipant> gameParticipants = gameTable.getGameParticipants();
        // GameParticipant killedPlayer = gameParticipants
        //     .stream()
        //     .filter(gameParticipant -> 
        //         gameParticipant.getPosition() == bestGameMoveRequest.getPlayerPosition()
        //     )
        //     .collect(Collectors.toList()).get(0);
        // if (killedPlayer.getRole() == PlayerRole.GODFATHER 
        //     || killedPlayer.getRole() == PlayerRole.MAFIA) {
        //     return 0.0;
        // }
        // for (GameParticipant gameParticipant: gameParticipants) {
        //     if (bestGameMoveRequest.getSuspectedPlayers().contains(gameParticipant.getPosition())) {
        //         result += gameParticipant.getRole() == PlayerRole.GODFATHER 
        //                     || gameParticipant.getRole() == PlayerRole.MAFIA ? 0.2 : 0;
        //     }
        // }
        return result;
    }
}
