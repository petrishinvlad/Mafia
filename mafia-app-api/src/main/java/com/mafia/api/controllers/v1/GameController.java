package com.mafia.api.controllers.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mafia.api.client.telegram.TelegramClientProvider;
import com.mafia.api.client.telegram.models.mappers.TelegramRequestMapper;
import com.mafia.api.models.requests.NewGamePollRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/game")
public class GameController {
    private final TelegramClientProvider telegramClient;

    private final TelegramRequestMapper telegramRequestMapper;

    @GetMapping("/test")
    public ResponseEntity<?> testEndpoint() {
        System.out.println("Here");
        NewGamePollRequest newGamePollRequest = NewGamePollRequest.builder()
                                                .chat_id(null)
                                                .is_anonymous(false)
                                                .address(null)
                                                .gameDate(null)
                                                .gameTime(null)
                                                .gameDay(null)
                                                .options(null)
                                                .build();
        telegramClient.sendPoll(telegramRequestMapper.fromNewGameRequestToPoll(newGamePollRequest));
        return ResponseEntity.ok("qwead");
    }
}
