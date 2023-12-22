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
// @Table(name = "GAME_PARTICIPANTS")
public class GameParticipant {
    // @Id
    // @GeneratedValue
    // @Column(name = "ID")
    private int id;

    //TODO
    // private PlayerRole role;

    // @Column(name = "points")
    // private double points;
}
