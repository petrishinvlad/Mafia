package com.mafia.api.bots.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.mafia.api.MafiaApplication;
import com.mafia.api.bots.exceptions.MafiaBotGameFinishException;
import com.mafia.api.bots.models.MafiaBotGame;
import com.mafia.api.bots.models.MafiaBotGameStatus;
import com.mafia.api.bots.repository.MafiaBotGameRepository;
import com.mafia.api.bots.services.MafiaBotService;
import com.mafia.api.security.WebSecurityConfig;

import jakarta.inject.Named;

@RunWith(SpringRunner.class)
@DataJpaTest(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb",
    "spring.jpa.hibernate.ddl-auto=create-drop"},
    includeFilters = 
        @ComponentScan.Filter(type = FilterType.ANNOTATION, 
                                classes = {Repository.class, 
                                            Component.class, 
                                            Service.class, 
                                            Controller.class, 
                                            Named.class,
                                            Configuration.class,
                                            Bean.class}),
    excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfig.class))
@ContextConfiguration(classes = {MafiaApplication.class})
public class MafiaBotServiceIntegrationTest {
    @Autowired
    private MafiaBotGameRepository mafiaBotGameRepository;

    @Autowired
    private MafiaBotService mafiaBotService;

    private static final LocalDateTime TEST_DATE_TIME = LocalDateTime.now();

    @Test
    @Description("Verify game announced when announceGame() executed")
    public void shouldStartGameWhenCommandInvoked() {
        mafiaBotService.announceGame(TEST_DATE_TIME);
        
        assertEquals("Wrong number of new games in repository",
            mafiaBotGameRepository.findAll().size(), 1);
        assertEquals("New game contains wrong status",
            MafiaBotGameStatus.GAME_ANNOUNCED.toString(), 
            mafiaBotGameRepository.findAll().get(0).getGameStatus().toString());
        assertEquals("New game contains wrong game time",
            TEST_DATE_TIME, 
            mafiaBotGameRepository.findAll().get(0).getGameTime());
    }

    @ParameterizedTest
    @EnumSource(value = MafiaBotGameStatus.class, names = {"GAME_STARTED", "GAME_PAUSED"})
    @Description("Verify game with correct status finished when finishGame() executed")            
    public void shouldFinishGameWhenCommandInvoked(MafiaBotGameStatus correctGameStatus) throws MafiaBotGameFinishException {
        MafiaBotGame gameToCreate = MafiaBotGame.builder()
                                        .gameStatus(correctGameStatus)
                                        .build();
        mafiaBotGameRepository.save(gameToCreate);
        assertEquals("Wrong number of new games in repository",
            mafiaBotGameRepository.findAll().size(), 1);
        MafiaBotGame game = mafiaBotGameRepository.findAll().get(0);
        
        mafiaBotService.finishGame(game.getId());
        
        assertEquals("New game contains wrong status",
            MafiaBotGameStatus.GAME_FINISHED.toString(), 
            mafiaBotGameRepository.findAll().get(0).getGameStatus().toString());
    }

    @ParameterizedTest
    @EnumSource(value = MafiaBotGameStatus.class, 
                names = {"GAME_STARTED", "GAME_PAUSED"}, 
                mode = EnumSource.Mode.EXCLUDE)
    @Description("Verify finishGame() throws exception for the game, that is not started")
    public void shouldThrowExceptionWhenFinishingGameWithWrongStatus(MafiaBotGameStatus exceptionGameStatus) {
        MafiaBotGame gameToCreate = MafiaBotGame.builder()
                                        .gameStatus(exceptionGameStatus)
                                        .build();
        mafiaBotGameRepository.save(gameToCreate);
        assertEquals("Wrong number of new games in repository",
                    mafiaBotGameRepository.findAll().size(), 1);
        MafiaBotGame game = mafiaBotGameRepository.findAll().get(0);
        
        Exception exception = assertThrows(Exception.class, () -> {
            mafiaBotService.finishGame(game.getId());
        });
        
        assertTrue("Wrong exception message for the exception, when game with wrong status is tried to be finished: " 
                    + exception.getMessage(),
            exception.getMessage().contains(MafiaBotService.GAME_FINISH_FAILED_WRONG_STATUS));
    }

    // @Test
    // @Description("Verify adding player to the announced game")
    // public void shouldAddPlayerToTheAnnouncedGame() {
    //     MafiaBotGame gameToCreate = MafiaBotGame.builder().build();
    //     mafiaBotGameRepository.save(gameToCreate);
    //     assertTrue(mafiaBotGameRepository.findAll().size() == 1);
    //     MafiaBotGame game = mafiaBotGameRepository.findAll().get(0);
        
    //     mafiaBotService.finishGame(game.getId());
        
    //     assertEquals("New game contains wrong status",
    //         MafiaBotGameStatus.GAME_FINISHED.toString(), 
    //         mafiaBotGameRepository.findAll().get(0).getGameStatus().toString());
    // }

    // @Test
    // @Description("Adding player for started game should throw exception")
    // public void shouldThrowExceptionWhenPlayerAddedForStartedGame() {

    // }

    // @Test
    // @Description("Adding player for full table should throw exception")
    // public void shouldThrowExceptionWhenPlayerAddedForFullTable() {
        
    // }

    // @Test
    // @Description("Starting game with empty slots should throw exception")
    // public void shouldThrowExceptionWhenGameStartedWithEmptySlots() {
        
    // }

    // @Test
    // @Description("Starting game when previous is not finished should throw exception")
    // public void shouldThrowExceptionWhenGameStartedBeforePreviousFinished() {
        
    // }

    // @Test
    // @Description("Setting foul when game is not in progress should throw exception")
    // public void shouldThrowExceptionWhenSettingFoulForNotStartedGame() {
        
    // }

    // @Test
    // @Description("Setting foul for non-existing position should throw exception")
    // public void shouldThrowExceptionWhenSettingFoulForNonExistingPosition() {
        
    // }

    // @Test
    // @Description("Setting foul should increment foul counter by 1")
    // public void shouldSetFoulForPlayer() {
        
    // }

    // @Test
    // @Description("Setting technical foul when game is not in progress should throw exception")
    // public void shouldThrowExceptionWhenSettingTechnicalFoulForNotStartedGame() {
        
    // }

    // @Test
    // @Description("Setting technical foul for non-existing position should throw exception")
    // public void shouldThrowExceptionWhenSettingTechnicalFoulForNonExistingPosition() {
        
    // }

    // @Test
    // @Description("Setting technical foul should increment techfoul counter by 1")
    // public void shouldSetTechnicalFoulForPlayer() {
        
    // }

    // @Test
    // @Description("Removing player for non-existing position should throw exception")
    // public void shouldThrowExceptionWhenRemovingPlayerForNonExistingPosition() {
        
    // }

    // @Test
    // @Description("Verify player removed when removePlayer executed")
    // public void shouldRemovePlayer() {
        
    // }
}