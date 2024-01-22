package com.mafia.api.models;

import java.time.LocalDateTime;
import java.util.Set;

import com.mafia.api.models.player.Player;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "GAME_TABLES")
public class GameTable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(name = "BEST_MOVE")
    private String bestMove;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "judgeId")
    private Player judge;

    @Column(name = "PLAYER_COMMENTS")
    private String playerComments; 

    @Column(name = "JUDGE_COMMENTS")
    private String judgeComments;

    @Column(name = "GAME_TIME")
    private LocalDateTime gameTime;

    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        mappedBy = "gameTable")
    private Set<GameParticipant> gameParticipants;
}
