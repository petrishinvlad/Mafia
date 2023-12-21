package com.mafia.api.client.messenger.telegram;

import com.mafia.api.client.messenger.MessengerClient;
import com.mafia.api.models.requests.MessengerPollRequest;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Named
public class TelegramClientProvider implements MessengerClient {
    private final TelegramClient telegramClient;

    public void sendPoll(final MessengerPollRequest pollRequest) {
        telegramClient.sendPoll();
        System.out.println(telegramClient.sendPoll());
        System.out.println("qwe123");
        // telegramClient.sendPoll(pollRequest);
    }

    public void pinPoll() {}
    public void getPollResults() {}
    public void showGameDayResults() {}
    public void showSeasonRating() {}
    public void showOverallRating() {}
}
