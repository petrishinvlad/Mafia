package com.mafia.api.models.requests;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NewGamePollRequest {
    private String chat_id;
    private boolean is_anonymous;
    private String question;
    @JsonIgnore
    private String gameDay;
    @JsonIgnore
    private String gameDate;
    @JsonIgnore
    private String gameTime;
    @JsonIgnore
    private String address;
    private List<String> options;
}
