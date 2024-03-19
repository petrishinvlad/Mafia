package com.mafia.api.models.requests;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateGamePlayerRequest {
    private String playerId;
    private String mafiaGameId;
    private String playerRole;
}
