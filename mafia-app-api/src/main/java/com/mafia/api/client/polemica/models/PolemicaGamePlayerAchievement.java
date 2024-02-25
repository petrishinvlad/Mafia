package com.mafia.api.client.polemica.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PolemicaGamePlayerAchievement {
    private Double sum;
    private List<PolemicaGamePlayerAchievementArray> array;
}
