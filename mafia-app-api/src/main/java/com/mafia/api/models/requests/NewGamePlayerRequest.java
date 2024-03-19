package com.mafia.api.models.requests;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NewGamePlayerRequest {
    private String mafiaGameId;
    private String playerId;
}
