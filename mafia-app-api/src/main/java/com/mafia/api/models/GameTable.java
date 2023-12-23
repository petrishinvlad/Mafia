package com.mafia.api.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@Table(name = "GAME_TABLES")
public class GameTable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(name = "BEST_MOVE")
    private String bestMove;

    //TODO
    // private final Player judge;

    @Column(name = "PLAYER_COMMENTS")
    private String playerComments; 

    @Column(name = "JUDGE_COMMENTS")
    private String judgeComments;

    @Column(name = "GAME_TIME")
    private LocalDateTime gameTime;
}
