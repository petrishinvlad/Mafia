package com.mafia.api.bots.utils;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MafiaBotUtils {
    public int getRandomElement(List<Integer> elements) {
        return elements.get(0);
    }
}
