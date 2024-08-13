package com.mafia.api.bots.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@Table(name = "MAFIA_BOT_USER")
public class MafiaBotUser {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    @Setter(AccessLevel.NONE)
    private long id;

    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        mappedBy = "user")
    private Set<MafiaBotUserRole> mafiaBotUserRoles;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "LASTNAME")
    private String lastname;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "SWIPEAL_ID")
    private String swipealPlayerId;

    @OneToMany(
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        mappedBy = "player")
    private Set<MafiaBotPlayer> mafiaPlayers = new HashSet<>();
}


