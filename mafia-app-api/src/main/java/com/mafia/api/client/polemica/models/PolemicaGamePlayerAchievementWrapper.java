package com.mafia.api.client.polemica.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PolemicaGamePlayerAchievementWrapper {
    private PolemicaAchievementsSumBestMove best_move;
    private PolemicaAchievementsSumVictory victory;
    private PolemicaAchievementsSumVoting voting;
}
