package com.mafia.api.client.messenger;

import com.mafia.api.models.requests.MessengerPollRequest;

public interface MessengerClient {
    void sendPoll(MessengerPollRequest pollRequest);
    void pinPoll();
    void getPollResults();
    void showGameDayResults();
    void showSeasonRating();
    void showOverallRating();
}
