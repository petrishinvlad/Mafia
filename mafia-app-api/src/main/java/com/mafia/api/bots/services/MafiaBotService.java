package com.mafia.api.bots.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.mafia.api.bots.exceptions.FullTableException;
import com.mafia.api.bots.exceptions.MafiaBotGameFinishException;
import com.mafia.api.bots.models.MafiaBotGame;
import com.mafia.api.bots.models.MafiaBotGameStatus;
import com.mafia.api.bots.repository.MafiaBotGameRepository;
import com.mafia.api.bots.utils.MafiaBotUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MafiaBotService {
    protected static final String GAME_FINISH_FAILED_WRONG_STATUS = "Game cannot be finished for game with status - ";
    private final MafiaBotGameRepository mafiaBotGameRepository;
    private static final int PLAYERS_COUNT = 10;

    protected boolean gameStarted = false;
    private final Map<String, Integer> usernamesWithPositions = new HashMap<>();//position 1 is 0 in this map, not the real position on the board
    private String[] playerUsernames = new String[PLAYERS_COUNT];
    private int[] playersWithFouls = new int[PLAYERS_COUNT];
    private int[] playersWithTechnicalFouls = new int[PLAYERS_COUNT];

    private final MafiaBotUtils utils;

    public void announceGame(LocalDateTime gameTime) {
        MafiaBotGame newGame = MafiaBotGame.builder()
                                .gameStatus(MafiaBotGameStatus.GAME_ANNOUNCED)
                                .gameTime(gameTime)
                                .build();
        mafiaBotGameRepository.save(newGame);
    }

    public void startGame(Long gameId) {
        MafiaBotGame finishedGame = mafiaBotGameRepository.findById(gameId).get();
        //TODO: verify players and judges
        finishedGame.setGameStatus(MafiaBotGameStatus.GAME_STARTED);
        mafiaBotGameRepository.save(finishedGame);
    }

    public void finishGame(Long gameId) throws MafiaBotGameFinishException {
        Set<MafiaBotGameStatus> gameStatusesToProceedWithFinishing = Set.of(
            MafiaBotGameStatus.GAME_STARTED, MafiaBotGameStatus.GAME_PAUSED
        );
        MafiaBotGame finishedGame = mafiaBotGameRepository.findById(gameId).get();
        MafiaBotGameStatus gameStatus = finishedGame.getGameStatus();
        if (!gameStatusesToProceedWithFinishing.contains(gameStatus)) {
            throw new MafiaBotGameFinishException(GAME_FINISH_FAILED_WRONG_STATUS);
        }
        finishedGame.setGameStatus(MafiaBotGameStatus.GAME_FINISHED);
        mafiaBotGameRepository.save(finishedGame);
    }

    public void addPlayer(String username) throws FullTableException {
        int position = getEmptyPosition();
        usernamesWithPositions.put(username, position);
        playerUsernames[position] = username;
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
