package com.mafia.api.models.requests;

import java.util.List;

import com.mafia.api.models.PlayerRole;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GameBestMoveRequest {
    private Integer playerPosition;
    private Integer gameId;
    private List<Integer> suspectedPlayers;
}
