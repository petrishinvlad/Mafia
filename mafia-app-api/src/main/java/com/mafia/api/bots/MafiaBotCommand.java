package com.mafia.api.bots;

public enum MafiaBotCommand {
    START("/start"),
    FINISH("/finish"),
    REMOVE("/remove"),
    FOUL("/foul"),
    TECH_FOUL("/techfoul"),
    ELIMINATE("/eliminate"),
    STATUS("/status"),
    TABLE("/table"),
    ADD_PLAYER("/addplayer"),
    CLEAR("/clear");

    private String value;

    MafiaBotCommand(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
