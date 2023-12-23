package com.mafia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mafia.api.models.GameTable;

public interface GameRepository extends JpaRepository<GameTable, Long> {
    
}
