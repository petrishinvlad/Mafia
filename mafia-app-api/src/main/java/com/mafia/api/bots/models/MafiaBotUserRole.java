package com.mafia.api.bots.models;

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
public class MafiaBotUserRole {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    @Setter(AccessLevel.NONE)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private MafiaBotUser user;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private MafiaBotUserRoleValue role;
}
