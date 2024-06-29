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
        //TODO: assert empty collections and their initialisation
    }

    @Test
    @Description("Verify game finished when finishGame executed")
    public void shouldFinishGameWhenCommandInvoked() {
        mafiaBotRepository.finishGame(TEST_USERNAME);
        assertFalse(mafiaBotRepository.gameStarted);
        //TODO: assert empty collections and their initialisation
    }

    @Test
    @Description("Adding player for started game should throw exception")
    public void shouldThrowExceptionWhenPlayerAddedForStartedGame() {

    }

    @Test
    @Description("Adding player for full table should throw exception")
    public void shouldThrowExceptionWhenPlayerAddedForFullTable() {
        
    }

    @Test
    @Description("Starting game with empty slots should throw exception")
    public void shouldThrowExceptionWhenGameStartedWithEmptySlots() {
        
    }

    @Test
    @Description("Starting game when previous is not finished should throw exception")
    public void shouldThrowExceptionWhenGameStartedBeforePreviousFinished() {
        
    }

    @Test
    @Description("Setting foul when game is not in progress should throw exception")
    public void shouldThrowExceptionWhenSettingFoulForNotStartedGame() {
        
    }

    @Test
    @Description("Setting foul for non-existing position should throw exception")
    public void shouldThrowExceptionWhenSettingFoulForNonExistingPosition() {
        
    }

    @Test
    @Description("Setting foul should increment foul counter by 1")
    public void shouldSetFoulForPlayer() {
        
    }

    @Test
    @Description("Setting technical foul when game is not in progress should throw exception")
    public void shouldThrowExceptionWhenSettingTechnicalFoulForNotStartedGame() {
        
    }

    @Test
    @Description("Setting technical foul for non-existing position should throw exception")
    public void shouldThrowExceptionWhenSettingTechnicalFoulForNonExistingPosition() {
        
    }

    @Test
    @Description("Setting technical foul should increment techfoul counter by 1")
    public void shouldSetTechnicalFoulForPlayer() {
        
    }

    @Test
    @Description("Removing player for non-existing position should throw exception")
    public void shouldThrowExceptionWhenRemovingPlayerForNonExistingPosition() {
        
    }

    @Test
    @Description("Verify player removed when removePlayer executed")
    public void shouldRemovePlayer() {
        
    }
}