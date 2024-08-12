package com.mafia.api.bots.models;

public enum MafiaBotGameStatus {
    GAME_ANNOUNCED("Game announced"),
    GAME_STARTED("Game started"),
    GAME_POSTPONED("Game postponed"),
    GAME_FINISHED("Game finished"),
    GAME_PAUSED("Game paused"),
    GAME_CANCELLED("Game cancelled"),
    GAME_RESULT_CANCELLED("Game result cancelled");

    private String value;
    
    MafiaBotGameStatus(String value) {
        this.value = getValue();
    }

    private String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
