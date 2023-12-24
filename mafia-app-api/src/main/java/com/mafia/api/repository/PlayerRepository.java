package com.mafia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mafia.api.models.player.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    
}
