package com.mafia.api.models.mappers;

import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.mafia.api.models.GameParticipant;
import com.mafia.api.models.GameTable;
import com.mafia.api.models.PlayerRole;
import com.mafia.api.models.player.Player;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameParticipantMapper {
    default GameParticipant participantsWithRolesToGameParticipant(final Integer participantId,
                                                                    final GameTable gameTable,
                                                                    final Map<Integer, PlayerRole> participantsWithRoles,
                                                                    final Map<Integer, Player> players) {
        return GameParticipant.builder()
                .player(players.get(participantId))
                .gameTable(gameTable)
                .role(participantsWithRoles.get(participantId))
                .build();
    }
}
