package com.mafia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mafia.api.models.GameParticipant;
import com.mafia.api.models.GameTable;

import java.util.List;


public interface GameParticipantRepository extends JpaRepository<GameParticipant, Integer> {
    List<GameParticipant> findByGameTable(GameTable gameTable);
}
