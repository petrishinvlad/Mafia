package com.mafia.api.bots.models;

import java.util.Set;

import com.mafia.api.models.GameTable;
import com.mafia.api.models.PlayerRole;
import com.mafia.api.models.player.Player;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name = "MAFIA_PLAYER")
public class MafiaBotPlayer {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    @Setter(AccessLevel.NONE)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="PLAYER_ID")
    private MafiaBotUser player;

    @ManyToOne
    @JoinColumn(name="GAME_ID")
    private MafiaBotGame game;

    @Column(name = "POSITION")
    private int position;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private MafiaBotGameRole role;

    @Column(name = "POINTS")
    private double points;

    @Column(name = "COMMENTS")
    private String comments;
}
