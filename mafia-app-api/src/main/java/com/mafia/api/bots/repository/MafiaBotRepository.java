package com.mafia.api.bots.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mafia.api.bots.exceptions.FullTableException;
import com.mafia.api.bots.utils.MafiaBotUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MafiaBotRepository {
    private static final int PLAYERS_COUNT = 10;

    protected boolean gameStarted = false;
    private final Map<String, Integer> usernamesWithPositions = new HashMap<>();//position 1 is 0 in this map, not the real position on the board
    private String[] playerUsernames = new String[PLAYERS_COUNT];
    private int[] playersWithFouls = new int[PLAYERS_COUNT];
    private int[] playersWithTechnicalFouls = new int[PLAYERS_COUNT];

    private final MafiaBotUtils utils;

    public void startGame(String username) {
        gameStarted = true;
        playerUsernames = new String[PLAYERS_COUNT];
        playersWithFouls = new int[PLAYERS_COUNT];
        playersWithTechnicalFouls = new int[PLAYERS_COUNT];
    }

    public void finishGame(String username) {
        gameStarted = false;
        playerUsernames = new String[PLAYERS_COUNT];
        playersWithFouls = new int[PLAYERS_COUNT];
        playersWithTechnicalFouls = new int[PLAYERS_COUNT];
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
