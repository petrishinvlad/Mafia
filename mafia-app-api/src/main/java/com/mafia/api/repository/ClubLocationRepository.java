package com.mafia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mafia.api.models.ClubLocation;

public interface ClubLocationRepository extends JpaRepository<ClubLocation, Integer> {
    
}
