package com.mafia.api.models.requests;

import java.time.LocalDateTime;
import java.util.Map;

import com.mafia.api.models.PlayerRole;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NewGameRequest {
    private String name;
    private LocalDateTime startTime;
    private Map<String, PlayerRole> participantsWithRoles;
}
