package com.mafia.api.models.requests;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateGameRequest {
    private String gameStatus;
    private boolean paused;
}
