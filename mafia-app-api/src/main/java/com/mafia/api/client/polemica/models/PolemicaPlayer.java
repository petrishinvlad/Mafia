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
public class PolemicaPlayer {
    private String id;
    private String username;
    private String image;
    // private PolemicaPlayerRole role;
    private Integer tablePosition;
    private String w_l;
    private String points;
    private Integer coins;
    private List<PolemicaGamePlayerAchievement> achievements;
    private PolemicaGamePlayerAchievementSum achievementsSum;
}
