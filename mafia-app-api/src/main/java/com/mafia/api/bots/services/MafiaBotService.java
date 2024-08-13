package com.mafia.api.bots.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.mafia.api.bots.exceptions.FullTableException;
import com.mafia.api.bots.exceptions.MafiaBotAddPlayerException;
import com.mafia.api.bots.exceptions.MafiaBotGameFinishException;
import com.mafia.api.bots.exceptions.MafiaBotGameStartException;
import com.mafia.api.bots.exceptions.MafiaBotSameUserInMultipleRolesException;
import com.mafia.api.bots.models.MafiaBotGame;
import com.mafia.api.bots.models.MafiaBotGameStatus;
import com.mafia.api.bots.models.MafiaBotPlayer;
import com.mafia.api.bots.models.MafiaBotUser;
import com.mafia.api.bots.repository.MafiaBotGameRepository;
import com.mafia.api.bots.repository.MafiaBotPlayerRepository;
import com.mafia.api.bots.utils.MafiaBotUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MafiaBotService {
    protected static final String GAME_START_FAILED_WRONG_NUMBER_OF_PLAYERS = "Game cannot start - not enough players registered";
    protected static final String GAME_FINISH_FAILED_WRONG_STATUS_PLACEHOLDER = "Game cannot be finished for game with status - ";
    protected static final String ADD_PLAYER_FAILED_PLAYER_REGISTERED_AS_JUDGE = "Player cannot be added, as he(she) is already game judge";
    protected static final String ADD_PLAYER_FAILED_TABLE_IS_FULL = "Player cannot be added, as the table is already full";
    protected static final String ADD_PLAYER_FAILED_WRONG_GAME_STATUS_PLACEHOLDER = "Player cannot be added, as game is in wrong status - ";

    protected static final int FULL_TABLE_PLAYERS_COUNT = 10; 

    private final MafiaBotGameRepository mafiaBotGameRepository;
    private final MafiaBotPlayerRepository mafiaBotPlayerRepository;
    //-----------------------------OLD NOT GOLD----------------------------
    private static final int PLAYERS_COUNT = 10;

    protected boolean gameStarted = false;
    private final Map<String, Integer> usernamesWithPositions = new HashMap<>();//position 1 is 0 in this map, not the real position on the board
    private String[] playerUsernames = new String[PLAYERS_COUNT];
    private int[] playersWithFouls = new int[PLAYERS_COUNT];
    private int[] playersWithTechnicalFouls = new int[PLAYERS_COUNT];

    private final MafiaBotUtils utils;
    //---------------------------------------------------------------------------
    
    public void announceGame(LocalDateTime gameTime) {
        MafiaBotGame newGame = MafiaBotGame.builder()
                                .gameStatus(MafiaBotGameStatus.GAME_ANNOUNCED)
                                .gameTime(gameTime)
                                .build();
        mafiaBotGameRepository.save(newGame);
    }

    public void startGame(Long gameId) {
        MafiaBotGame gameToStart = mafiaBotGameRepository.findById(gameId).get();
        Set<MafiaBotPlayer> players = gameToStart.getPlayers();
        try {
            verifyPlayers(players);
            gameToStart.setGameStatus(MafiaBotGameStatus.GAME_STARTED);
            mafiaBotGameRepository.save(gameToStart);
        } catch (MafiaBotGameStartException ex) {
            ex.printStackTrace();
        }
    }

    private void verifyPlayers(Set<MafiaBotPlayer> players) throws MafiaBotGameStartException {
        validatePlayersCountBeforeGameStart(players);
        // TODO: validate judge is not player
        // TODO: validate all players are unique
    }

    private void validatePlayersCountBeforeGameStart(Set<MafiaBotPlayer> players) throws MafiaBotGameStartException {
        if (players.size() != FULL_TABLE_PLAYERS_COUNT) {
            throw new MafiaBotGameStartException(GAME_START_FAILED_WRONG_NUMBER_OF_PLAYERS);
        }
    }

    public void finishGame(Long gameId) throws MafiaBotGameFinishException {
        MafiaBotGame gameToFinish = mafiaBotGameRepository.findById(gameId).get();
        validateGameBeforeFinishingGame(gameToFinish);
        gameToFinish.setGameStatus(MafiaBotGameStatus.GAME_FINISHED);
        mafiaBotGameRepository.save(gameToFinish);
    }

    private void validateGameBeforeFinishingGame(MafiaBotGame gameToFinish) throws MafiaBotGameFinishException {
        verifyGameStatusBeforeFinishingGame(gameToFinish);
    }

    private void verifyGameStatusBeforeFinishingGame(MafiaBotGame gameToFinish) throws MafiaBotGameFinishException {
        Set<MafiaBotGameStatus> gameStatusesToProceedWithFinishing = Set.of(
            MafiaBotGameStatus.GAME_STARTED, MafiaBotGameStatus.GAME_PAUSED
        );
        MafiaBotGameStatus gameStatus = gameToFinish.getGameStatus();
        if (!gameStatusesToProceedWithFinishing.contains(gameStatus)) {
            throw new MafiaBotGameFinishException(GAME_FINISH_FAILED_WRONG_STATUS_PLACEHOLDER + gameStatus);
        }
    }

    public void addPlayer(MafiaBotGame game, MafiaBotUser user) throws MafiaBotAddPlayerException {
        Set<MafiaBotPlayer> registeredPlayers = game.getPlayers();
        MafiaBotUser judge = game.getMafiaJudge();
        validateAddingPlayer(game.getGameStatus(), registeredPlayers, judge);

        MafiaBotPlayer player = MafiaBotPlayer.builder().game(game).player(user).build();
        mafiaBotPlayerRepository.save(player);
    }

    private void validateAddingPlayer(MafiaBotGameStatus gameStatus, 
                                        Set<MafiaBotPlayer> registeredPlayers, 
                                        MafiaBotUser judge) throws MafiaBotAddPlayerException  {
        verifyCorrectGameStatusBeforeAddingPlayer(gameStatus);
        verifyTableIsNotFull(registeredPlayers);
        verifyPlayerIsNotJudge(registeredPlayers, judge);
    }

    private void verifyCorrectGameStatusBeforeAddingPlayer(MafiaBotGameStatus gameToAddPlayerStatus) throws MafiaBotAddPlayerException {
        Set<MafiaBotGameStatus> gameStatuses = Set.of(
            MafiaBotGameStatus.GAME_ANNOUNCED, MafiaBotGameStatus.GAME_POSTPONED
        );
        if (!gameStatuses.contains(gameToAddPlayerStatus)) {
            throw new MafiaBotAddPlayerException(ADD_PLAYER_FAILED_WRONG_GAME_STATUS_PLACEHOLDER 
                                                    + gameToAddPlayerStatus);
        }
    }

    private void verifyTableIsNotFull(Set<MafiaBotPlayer> registeredPlayers) throws MafiaBotAddPlayerException {
        if (registeredPlayers.size() == FULL_TABLE_PLAYERS_COUNT) {
            throw new MafiaBotAddPlayerException(ADD_PLAYER_FAILED_TABLE_IS_FULL);
        }
    }

    private void verifyPlayerIsNotJudge(Set<MafiaBotPlayer> registeredPlayers, MafiaBotUser judge) throws MafiaBotAddPlayerException {
        for (MafiaBotPlayer player: registeredPlayers) {
            if (player.getPlayer().getId() == judge.getId()) {
                throw new MafiaBotAddPlayerException(ADD_PLAYER_FAILED_PLAYER_REGISTERED_AS_JUDGE);
            }
        }
    }

    public void removePlayer(int position) {
        String username = playerUsernames[position];
        usernamesWithPositions.remove(username);
        playersWithFouls[position] = 0;
        playersWithTechnicalFouls[position] = 0;
    }

    public void setFoul(int position) {
        playersWithFouls[position]++;
    }

    public void setTechnicalFoul(int position) {
        playersWithTechnicalFouls[position]++;
    }

    //TODO: rewrite in lambda mode
    private List<Integer> getEmptyPositions() {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < PLAYERS_COUNT; ++i) {
            if (playerUsernames[i] == null) {
                result.add(i);
            }
        }
        return result;
    }

    private int getEmptyPosition() throws FullTableException {
        List<Integer> emptyPositions = getEmptyPositions();
        if (!emptyPositions.isEmpty()) {
            throw new FullTableException("Table is already full, please wait in a queue");
        }
        return utils.getRandomElement(emptyPositions);
    }
}
