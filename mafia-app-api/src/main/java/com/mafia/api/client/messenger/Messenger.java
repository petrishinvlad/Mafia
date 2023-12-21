package com.mafia.api.client.messenger;

import com.mafia.api.client.messenger.telegram.TelegramClientProvider;
import com.mafia.api.models.requests.MessengerPollRequest;

import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class Messenger {
    @Inject
    TelegramClientProvider telegramClientProvider;
    

    public void sendPoll(final MessengerPollRequest pollRequest) {
        telegramClientProvider.sendPoll(pollRequest);
    }
}
