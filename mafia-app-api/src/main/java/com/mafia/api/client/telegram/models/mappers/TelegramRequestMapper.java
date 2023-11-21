package com.mafia.api.client.telegram.models.mappers;

import com.mafia.api.models.requests.MessengerPollRequest;
import com.mafia.api.models.requests.NewGamePollRequest;

public class TelegramRequestMapper {
    public MessengerPollRequest fromNewGameRequestToPoll(final NewGamePollRequest newGamePollRequest) {
        String question = newGamePollRequest.getAddress();
        return MessengerPollRequest.builder()
                .chat_id(newGamePollRequest.getChat_id())
                .is_anonymous(newGamePollRequest.is_anonymous())
                .question(question)
                .options(newGamePollRequest.getOptions())
                .build();
    }
}
