package com.mafia.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "CLUB_LOCATIONS")
public class ClubLocation {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;
    
    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CITY")
    private String city;
    
    @Column(name = "ADDRESS")
    private String address;
}
