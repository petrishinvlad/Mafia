package com.mafia.api.models;

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
// @Table(name = "GAME_DAYS")
public class GameDay {
    // @Id
    // @GeneratedValue
    // @Column(name = "ID")
    private int id;

    //TODO
    // private ClubLocation location;

    //TODO
    // private MafiaClub club;

    //TODO
    // private GameSeason gameSeason;
}
