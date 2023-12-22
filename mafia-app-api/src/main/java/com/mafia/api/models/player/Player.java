package com.mafia.api.models.player;

import java.util.List;

import com.mafia.api.models.MafiaClub;

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
// @Table(name = "PLAYERS")
public class Player {
    // @Id
    // @GeneratedValue
    // @Column(name = "ID")
    private int id;

    // @Column(name = "NICKNAME")
    // private String nickname;

    // @Column(name = "FIRSTNAME")
    // private String firstname;

    // @Column(name = "LASTNAME")
    // private String lastname;

    // @Column(name = "LOCATION")
    // private String location;

    //TODO
    // private List<MafiaClub> mafiaClubs;
}
