package com.mafia.api.bots.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mafia.api.bots.models.MafiaBotGame;

@Repository
public interface MafiaBotGameRepository extends JpaRepository<MafiaBotGame, Long> {
    
}
