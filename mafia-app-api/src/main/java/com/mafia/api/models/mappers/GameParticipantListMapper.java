package com.mafia.api.models.mappers;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import com.mafia.api.models.GameParticipant;
import com.mafia.api.models.GameTable;
import com.mafia.api.models.PlayerRole;
import com.mafia.api.models.player.Player;
import com.mafia.api.models.requests.NewGameRequest;
import com.mafia.api.repository.PlayerRepository;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class GameParticipantListMapper {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameParticipantMapper gameParticipantMapper;

    public List<GameParticipant> newGameRequestToGameParticipants(final NewGameRequest newGameRequest, 
                                                                    final GameTable gameTable) {
        Map<Integer, PlayerRole> participantsWithRoles = newGameRequest.getParticipantsWithRoles();
        Map<Integer, Player> players = playerRepository
            .findAllById(participantsWithRoles.keySet())
            .stream()
            .collect(Collectors.toMap(Player::getId, Function.identity()));

        List<GameParticipant> gameParticipants = (List<GameParticipant>) participantsWithRoles.keySet()
            .stream()
            .map(participantId -> gameParticipantMapper.participantsWithRolesToGameParticipant(participantId, 
                                                                                                gameTable, 
                                                                                                participantsWithRoles, 
                                                                                                players))
            .collect(Collectors.toList());

        return gameParticipants;
    }

}
