package com.mafia.api.client.polemica.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PolemicaGamePlayerAchievementSum {
    private Double points;
    private PolemicaGamePlayerAchievementWrapper achievements;
}
