package com.mafia.api.client.polemica.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mafia.api.client.polemica.models.PolemicaPlayer;
import com.mafia.api.models.GameParticipant;

@Mapper
public interface PolemicaMapper {
    // @Mapping(source = "id", target = "player.polemicaPlayerId")
    GameParticipant polemicaPlayerToParticipant(PolemicaPlayer player);
}
