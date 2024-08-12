package com.mafia.api.bots.models;

public enum MafiaBotGameRole {
    CIVILIAN("Civilian"),
    MAFIA("Mafia"),
    SHERIFF("Sheriff"),
    GODFATHER("Godfather");

    private String value;

    MafiaBotGameRole(String value) {
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
