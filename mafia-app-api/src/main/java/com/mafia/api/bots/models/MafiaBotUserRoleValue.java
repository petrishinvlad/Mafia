package com.mafia.api.bots.models;

public enum MafiaBotUserRoleValue {
    ADMIN("admin"),
    JUDGE("judge"),
    PLAYER("player");

    private String value;

    MafiaBotUserRoleValue(final String value) {
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
