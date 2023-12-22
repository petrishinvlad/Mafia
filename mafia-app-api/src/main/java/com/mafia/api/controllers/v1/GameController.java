package com.mafia.api.controllers.v1;

import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mafia.api.client.messenger.Messenger;
import com.mafia.api.models.requests.NewGamePollRequest;
import com.mafia.api.repository.MafiaClubRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/game")
public class GameController {
    private final Messenger messenger;

    // private final MafiaClubRepository clubRepository;

    @GetMapping("/test")
    public ResponseEntity<?> testEndpoint() {
        NewGamePollRequest newGamePollRequest = NewGamePollRequest.builder()
                                                .chat_id(null)
                                                .is_anonymous(false)
                                                .address(null)
                                                .gameDate(null)
                                                .gameTime(null)
                                                .gameDay(null)
                                                .options(null)
                                                .build();
        // System.out.println(jdbcTemplate.queryForList("SELECT * FROM Player"));
        messenger.sendPoll(null);
        // telegramClient.sendPoll(telegramRequestMapper.fromNewGameRequestToPoll(newGamePollRequest));
        return ResponseEntity.ok("qwead");
    }
}
