package com.mafia.api.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.mafia.api.client.messenger.telegram.TelegramClientProvider;
import com.mafia.api.models.GameParticipant;
import com.mafia.api.models.GameTable;
import com.mafia.api.models.PlayerRole;
import com.mafia.api.models.requests.NewGamePollRequest;
import com.mafia.api.models.requests.NewGameRequest;
import com.mafia.api.repository.GameParticipantRepository;
import com.mafia.api.repository.GameRepository;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Named
public class GameService {
    private final TelegramClientProvider telegramClient;
    private final GameRepository gameRepository; 
    private final GameParticipantRepository gameParticipantRepository;

    public List<NewGamePollRequest> getAllNearestGamePolls() {
        return List.of();
    }


    public void createNewPoll(final NewGamePollRequest newGamePollRequest) {

    }

    public GameTable createNewGame(final NewGameRequest newGameRequest) {
        GameTable game = GameTable.builder()
                        .gameTime(newGameRequest.getStartTime())
                        .build();
        gameRepository.save(game);

        Map<String, PlayerRole> participantsWithRoles = newGameRequest.getParticipantsWithRoles();
        List<GameParticipant> gameParticipants = (List<GameParticipant>) participantsWithRoles.keySet().stream().map(participantId -> GameParticipant.builder()/*.playerId(participantId)*/.build()).collect(Collectors.toList());
        gameParticipantRepository.saveAll(gameParticipants);
        
        return game;
    }
}
