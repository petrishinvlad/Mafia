package com.mafia.api.models;

import java.util.Map.Entry;

import com.mafia.api.models.player.Player;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@Table(name = "GAME_PARTICIPANTS")
public class GameParticipant {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    @Setter(AccessLevel.NONE)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playerId")
    private Player player;
    
    @Column(name = "position")
    private Integer position;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private PlayerRole role;

    @Column(name = "points")
    private double points;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gameTableId")
    private GameTable gameTable;

    @Column(name = "comments")
    private String comments;
}
