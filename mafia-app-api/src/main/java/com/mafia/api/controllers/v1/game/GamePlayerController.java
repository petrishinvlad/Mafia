package com.mafia.api.controllers.v1.game;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mafia.api.models.GameParticipant;
import com.mafia.api.models.requests.NewGamePlayerRequest;
import com.mafia.api.models.requests.UpdateGamePlayerRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/")
public class GamePlayerController {
    @PostMapping("/gameplayer")
    public ResponseEntity<GameParticipant> addPlayer(@RequestBody NewGamePlayerRequest gamePlayerRequest) {
        GameParticipant result = GameParticipant.builder().build();
        return ResponseEntity.ok().body(result);
    }   

    @PatchMapping("/gameplayer")
    public ResponseEntity<GameParticipant> updatePlayer(@RequestBody UpdateGamePlayerRequest updateGamePlayerRequest) {
        GameParticipant result = GameParticipant.builder().build();
        return ResponseEntity.ok().body(result);
    }   
}
