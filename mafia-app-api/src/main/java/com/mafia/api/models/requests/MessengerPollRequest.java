package com.mafia.api.models.requests;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
public class MessengerPollRequest {
    private String chat_id;
    private boolean is_anonymous;
    private String question;
    private List<String> options;
}