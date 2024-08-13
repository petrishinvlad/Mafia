package com.mafia.api.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import com.mafia.api.models.GameTable;
import com.mafia.api.models.player.Player;
import com.mafia.api.models.requests.NewGameRequest;
import com.mafia.api.repository.PlayerRepository;

// @Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class GameTableMapper {
    // @Autowired
    // private PlayerRepository playerRepository;

    // public GameTable newGameRequestToGameTable(final NewGameRequest newGameRequest) {
    //     Player judge = newGameRequest.isJudgeInGame() ? playerRepository.findById(newGameRequest.getJudge()).get() : null;
    //     return GameTable.builder()
    //                     .gameTime(newGameRequest.getStartTime())
    //                     .judge(judge)
    //                     .build();
    // }
}
