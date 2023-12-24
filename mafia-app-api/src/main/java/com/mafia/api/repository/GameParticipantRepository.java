package com.mafia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mafia.api.models.GameParticipant;

public interface GameParticipantRepository extends JpaRepository<GameParticipant, Integer> {
    
}
