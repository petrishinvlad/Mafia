package com.mafia.api.models;

import java.util.HashSet;
import java.util.Set;

import com.mafia.api.bots.models.MafiaBotUser;
import com.mafia.api.models.player.Player;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@Table(name = "MAFIA_CLUBS")
public class MafiaClub {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    // TODO: get only players in this collection
    // @ManyToMany(mappedBy = "mafiaClubs")
    // private Set<MafiaBotUser> players = new HashSet<>();
}