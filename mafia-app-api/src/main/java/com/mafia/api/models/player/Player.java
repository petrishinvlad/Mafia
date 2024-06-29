package com.mafia.api.models.player;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PLAYERS")
public class Player {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "LASTNAME")
    private String lastname;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "POLEMICAID")
    private String swipealPlayerId;


    //TODO
    // private List<MafiaClub> mafiaClubs;
}
