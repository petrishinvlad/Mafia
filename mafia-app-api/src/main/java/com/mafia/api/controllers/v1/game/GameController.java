package com.mafia.api.controllers.v1.game;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mafia.api.client.messenger.Messenger;
import com.mafia.api.client.polemica.PolemicaClient;
import com.mafia.api.models.ClubLocation;
import com.mafia.api.models.GameTable;
import com.mafia.api.models.requests.NewGamePollRequest;
import com.mafia.api.models.requests.NewGameRequest;
import com.mafia.api.repository.ClubLocationRepository;
import com.mafia.api.repository.GameRepository;
import com.mafia.api.repository.MafiaClubRepository;
import com.mafia.api.service.GameService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/")
public class GameController {
    private final Messenger messenger;

    private final GameService gameService;

    private final JdbcTemplate jdbcTemplate;

    private final MafiaClubRepository mafiaClubRepository;
    private final GameRepository gameRepository;
    private final ClubLocationRepository clubLocationRepository;

    private final PolemicaClient polemica;

    @GetMapping("/game")
    public ResponseEntity<?> getGame() {
        // NewGamePollRequest newGamePollRequest = NewGamePollRequest.builder()
        //                                         .chat_id(null)
        //                                         .is_anonymous(false)
        //                                         .address(null)
        //                                         .gameDate(null)
        //                                         .gameTime(null)
        //                                         .gameDay(null)
        //                                         .options(null)
        //                                         .build();
        // messenger.sendPoll(null);
        var polemicaResponse = polemica.polemicaGameStats("221645");
        return ResponseEntity.ok("qweads");
    } 

    @PostMapping("/game")
    public ResponseEntity<GameTable> createGame(@RequestBody NewGameRequest newGameRequest) {
        GameTable newGame = gameService.createNewGame(newGameRequest);
        return ResponseEntity.ok().body(newGame);
    }

    @PatchMapping("/game")
    public ResponseEntity<GameTable> updateGame(@RequestBody GameTable gameTable) {
        return ResponseEntity.ok().body(gameTable);
    }
}
