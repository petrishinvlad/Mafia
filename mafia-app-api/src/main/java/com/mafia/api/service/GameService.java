package com.mafia.api.service;

import java.util.List;

import com.mafia.api.client.messenger.telegram.TelegramClientProvider;
import com.mafia.api.models.requests.NewGamePollRequest;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Named
public class GameService {
    private final TelegramClientProvider telegramClient;

    public List<NewGamePollRequest> getAllNearestGamePolls() {
        return List.of();
    }


    public void createNewPoll(final NewGamePollRequest newGamePollRequest) {

    }

}
