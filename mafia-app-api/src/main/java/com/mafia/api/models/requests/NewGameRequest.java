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
    private int judge;
    private LocalDateTime startTime;
    private Map<Integer, PlayerRole> participantsWithRoles;
}
