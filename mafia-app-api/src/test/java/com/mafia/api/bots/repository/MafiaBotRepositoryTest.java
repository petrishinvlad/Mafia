package com.mafia.api.bots.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Description;

import com.mafia.api.bots.utils.MafiaBotUtils;

@ExtendWith(MockitoExtension.class)
public class MafiaBotRepositoryTest {
    @Mock
    private MafiaBotUtils mafiaBotUtils;

    @InjectMocks 
    private MafiaBotRepository mafiaBotRepository;

    private static final String TEST_USERNAME = "testuser";

    @Test
    @Description("Verify game started when startGame executed")
    public void shouldStartGameWhenCommandInvoked() {
        mafiaBotRepository.startGame(TEST_USERNAME);
        assertTrue(mafiaBotRepository.gameStarted);
    }

    @Test
    @Description("Verify game finished when finishGame executed")
    public void shouldFinishGameWhenCommandInvoked() {
        mafiaBotRepository.finishGame(TEST_USERNAME);
        assertFalse(mafiaBotRepository.gameStarted);
    }
}
