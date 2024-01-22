package com.mafia.api.models.requests;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GameRequest {
    private Integer tableId;
    private int judge;
    private String playerComments;
    private String judgeComments;
    private LocalDateTime startTime;
    private Map<Integer, Double> participantsWithPoints;
}
