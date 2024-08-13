package com.mafia.api.bots.exceptions;

public class MafiaBotSameUserInMultipleRolesException extends Exception {
    public MafiaBotSameUserInMultipleRolesException(String errorMessage) {
        super(errorMessage);
    }
}
