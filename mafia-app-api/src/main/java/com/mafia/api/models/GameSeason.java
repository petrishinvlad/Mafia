package com.mafia.api.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
// @Entity
// @Table(name = "GAME_SEASONS")
public class GameSeason {
    // @Id
    // @GeneratedValue
    // @Column(name = "ID")
    private int id;

    // @Column(name = "START_TIME")
    // private LocalDateTime startTime;

    // @Column(name = "END_TIME")
    // private LocalDateTime endTime;

    // @Column(name = "LOCATION")
    // private ClubLocation location;

    //TODO
    // private MafiaClub club;
}
