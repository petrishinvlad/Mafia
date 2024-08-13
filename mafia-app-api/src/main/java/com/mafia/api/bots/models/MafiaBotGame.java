package com.mafia.api.bots.models;

import java.time.LocalDateTime;
import java.util.Set;

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
@Table(name = "MAFIA_GAME")
public class MafiaBotGame {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    @Setter(AccessLevel.NONE)
    private long id;

    @ManyToOne
    @JoinColumn(name="MAFIAJUDGE_ID")
    private MafiaBotUser mafiaJudge;

    @Column(name = "GAME_STATUS")
    private MafiaBotGameStatus gameStatus;

    @Column(name = "GAME_TIME")
    private LocalDateTime gameTime;

    @Column(name = "BEST_MOVE")
    private String bestMove;

    @Column(name = "PLAYER_COMMENTS")
    private String playerComments; 

    @Column(name = "JUDGE_COMMENTS")
    private String judgeComments;

    @OneToMany(
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        mappedBy = "game")
    private Set<MafiaBotPlayer> players;
}
