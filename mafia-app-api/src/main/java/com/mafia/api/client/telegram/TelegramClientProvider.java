package com.mafia.api.client.telegram;

import org.springframework.stereotype.Component;

import com.mafia.api.models.requests.MessengerPollRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class TelegramClientProvider {
    private final TelegramClient telegramClient;

    public void sendPoll(final MessengerPollRequest pollRequest) {
        telegramClient.sendPoll(pollRequest);
    }
}
