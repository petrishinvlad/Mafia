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
public class PolemicaGameStats {
    private String id;
    private String type;
    private Double daysNumber;
    private Integer winnerCode;

    private List<PolemicaPlayer> players;

    private String firstKilled;
}
