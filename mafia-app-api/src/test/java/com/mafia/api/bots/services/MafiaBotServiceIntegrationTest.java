package com.mafia.api.bots.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
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
import com.mafia.api.bots.exceptions.MafiaBotAddPlayerException;
import com.mafia.api.bots.exceptions.MafiaBotGameFinishException;
import com.mafia.api.bots.models.MafiaBotGame;
import com.mafia.api.bots.models.MafiaBotGameStatus;
import com.mafia.api.bots.models.MafiaBotPlayer;
import com.mafia.api.bots.models.MafiaBotUser;
import com.mafia.api.bots.repository.MafiaBotGameRepository;
import com.mafia.api.bots.repository.MafiaBotPlayerRepository;
import com.mafia.api.bots.repository.MafiaBotUserRepository;
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
    private MafiaBotUserRepository mafiaBotUserRepository;

    @Autowired
    private MafiaBotPlayerRepository mafiaBotPlayerRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MafiaBotService mafiaBotService;

    private static final LocalDateTime TEST_DATE_TIME = LocalDateTime.now();

    @Test
    @Description("Verify game announced when announceGame() executed")
    public void shouldStartGameWhenCommandInvoked() {
        mafiaBotService.announceGame(TEST_DATE_TIME);
        
        assertEquals("Wrong number of new games in repository",
            1, mafiaBotGameRepository.count());
        MafiaBotGame announcedGame = mafiaBotGameRepository.findAll().get(0);
        assertEquals("New game contains wrong status",
            MafiaBotGameStatus.GAME_ANNOUNCED.toString(), 
            announcedGame.getGameStatus().toString());
        assertEquals("New game contains wrong game time",
            TEST_DATE_TIME, 
            announcedGame.getGameTime());
    }

    @ParameterizedTest
    @EnumSource(value = MafiaBotGameStatus.class, names = {"GAME_STARTED", "GAME_PAUSED"})
    @Description("Verify game with correct status finished when finishGame() executed")            
    public void shouldFinishGameWhenCommandInvoked(MafiaBotGameStatus correctGameStatus) throws MafiaBotGameFinishException {
        MafiaBotGame game = createAndSaveMafiaBotGame(correctGameStatus);
        assertEquals("Wrong number of new games in repository",
            1, mafiaBotGameRepository.count());
        
        mafiaBotService.finishGame(game.getId());
        
        assertEquals("New game contains wrong status",
            MafiaBotGameStatus.GAME_FINISHED.toString(), 
            game.getGameStatus().toString());
    }

    @ParameterizedTest
    @EnumSource(value = MafiaBotGameStatus.class, 
                names = {"GAME_STARTED", "GAME_PAUSED"}, 
                mode = EnumSource.Mode.EXCLUDE)
    @Description("Verify finishGame() throws exception for the game, that is not started")
    public void shouldThrowExceptionWhenFinishingGameWithWrongStatus(MafiaBotGameStatus exceptionGameStatus) {
        MafiaBotGame game = createAndSaveMafiaBotGame(exceptionGameStatus);
        assertEquals("Wrong number of new games in repository",
                    1, mafiaBotGameRepository.count());
        
        Exception exception = assertThrows(Exception.class, () -> {
            mafiaBotService.finishGame(game.getId());
        });
        
        assertTrue("Wrong exception message, when game with wrong status is tried to be finished: " 
                    + exception.getMessage(),
            exception.getMessage().equals(MafiaBotService.GAME_FINISH_FAILED_WRONG_STATUS_PLACEHOLDER + exceptionGameStatus.toString()));
    }

    @ParameterizedTest
    @EnumSource(value = MafiaBotGameStatus.class, 
                names = {"GAME_ANNOUNCED", "GAME_POSTPONED"})
    @Description("Verify adding player to the game with correct status")
    public void shouldAddPlayerWhenGameInCorrectStatus(MafiaBotGameStatus correctGameStatus) 
                                    throws MafiaBotAddPlayerException {
        MafiaBotUser user = createAndSaveMafiaBotUser("@abcdef");
        assertEquals("Wrong number of new users in repository",
                        mafiaBotUserRepository.count(), 1);
        MafiaBotGame game = createAndSaveMafiaBotGame(correctGameStatus);
        assertEquals("Wrong number of new games in repository",
                        mafiaBotGameRepository.count(), 1);
        testEntityManager.detach(game);
        //required to have 
        game = mafiaBotGameRepository.findById(game.getId()).get();

        mafiaBotService.addPlayer(game.getId(), user.getTelegramNickname());

        List<MafiaBotPlayer> players = mafiaBotPlayerRepository.findAll();
        assertEquals("Wrong number of new players in repository",
                        1, players.size());
        MafiaBotPlayer player = players.get(0);
        assertEquals("Wrong game assigned to player",
                        player.getGame().getId(), game.getId());
        assertEquals("Wrong user assigned to player",
                        player.getPlayer().getId(), user.getId());
    }
    
    @ParameterizedTest
    @EnumSource(value = MafiaBotGameStatus.class, 
                names = {"GAME_ANNOUNCED", "GAME_POSTPONED"},
                mode = EnumSource.Mode.EXCLUDE)
    @Description("Verify adding player throws exception for the game with incorrect status")
    public void shouldThrowExceptionWhenAddingPlayerForGameWithWrongStatus(MafiaBotGameStatus exceptionGameStatus) {
        MafiaBotUser user = createAndSaveMafiaBotUser("@abcdef");
        assertEquals("Wrong number of new users in repository",
                        1, mafiaBotUserRepository.count());
        MafiaBotGame gameToCreate = createAndSaveMafiaBotGame(exceptionGameStatus);
        assertEquals("Wrong number of new games in repository",
                        1, mafiaBotGameRepository.count());
        testEntityManager.detach(gameToCreate);
        MafiaBotGame game = mafiaBotGameRepository.findById(gameToCreate.getId()).get();
        
        Exception exception = assertThrows(MafiaBotAddPlayerException.class, () -> {
            mafiaBotService.addPlayer(game.getId(), user.getTelegramNickname());
        });
        
        assertTrue("Wrong exception message for the exception, when game with wrong status is tried to be finished: " 
                    + exception.getMessage(),
            exception.getMessage().equals(MafiaBotService.ADD_PLAYER_FAILED_WRONG_GAME_STATUS_PLACEHOLDER + exceptionGameStatus.toString()));
    }
    
    @Test
    @Description("Verify adding player throws exception for the full game")
    public void shouldThrowExceptionWhenAddingPlayerForFullGame() {
        MafiaBotGame game = createAndSaveMafiaBotGame(MafiaBotGameStatus.GAME_ANNOUNCED);
        testEntityManager.detach(game);
        List<MafiaBotUser> usersToCreate = new ArrayList<>();
        List<MafiaBotPlayer> playersToCreate = new ArrayList<>();
        for (int i = 0; i < MafiaBotService.FULL_TABLE_PLAYERS_COUNT; ++i) {
            usersToCreate.add(createMafiaBotUser("@abcdef" + i));
        }
        mafiaBotUserRepository.saveAllAndFlush(usersToCreate);
        for (int i = 0; i < MafiaBotService.FULL_TABLE_PLAYERS_COUNT; ++i) {
            MafiaBotPlayer player = createMafiaBotPlayer(game, usersToCreate.get(i));
            playersToCreate.add(player);
        }
        mafiaBotPlayerRepository.saveAllAndFlush(playersToCreate);
        assertEquals("Wrong number of new players in repository",
                        mafiaBotPlayerRepository.count(), MafiaBotService.FULL_TABLE_PLAYERS_COUNT);
        
        MafiaBotUser user = createAndSaveMafiaBotUser("@abcdef");

        Exception exception = assertThrows(MafiaBotAddPlayerException.class, () -> {
            mafiaBotService.addPlayer(game.getId(), user.getTelegramNickname());
        });
        
        assertTrue("Wrong exception message, when game with wrong status is tried to be finished: " 
                    + exception.getMessage(),
            exception.getMessage().equals(MafiaBotService.ADD_PLAYER_FAILED_TABLE_IS_FULL));
    }

    @Test
    @Description("Verify adding player throws exception for the player, registered as judge")
    public void shouldThrowExceptionWhenAddingPlayerRegisteredAsJudge() {
        MafiaBotGame game = createAndSaveMafiaBotGame(MafiaBotGameStatus.GAME_ANNOUNCED);
        testEntityManager.detach(game);
        List<MafiaBotUser> usersToCreate = new ArrayList<>();
        List<MafiaBotPlayer> playersToCreate = new ArrayList<>();
        for (int i = 0; i < MafiaBotService.FULL_TABLE_PLAYERS_COUNT; ++i) {
            usersToCreate.add(createMafiaBotUser("@abcdef" + i));
        }
        mafiaBotUserRepository.saveAllAndFlush(usersToCreate);
        for (int i = 0; i < MafiaBotService.FULL_TABLE_PLAYERS_COUNT; ++i) {
            MafiaBotPlayer player = createMafiaBotPlayer(game, usersToCreate.get(i));
            playersToCreate.add(player);
        }
        mafiaBotPlayerRepository.saveAllAndFlush(playersToCreate);
        assertEquals("Wrong number of new players in repository",
                        mafiaBotPlayerRepository.count(), MafiaBotService.FULL_TABLE_PLAYERS_COUNT);
        
        MafiaBotUser user = createAndSaveMafiaBotUser("@abcdef");

        Exception exception = assertThrows(MafiaBotAddPlayerException.class, () -> {
            mafiaBotService.addPlayer(game.getId(), user.getTelegramNickname());
        });
        
        assertTrue("Wrong exception message, when game with wrong status is tried to be finished: " 
                    + exception.getMessage(),
            exception.getMessage().equals(MafiaBotService.ADD_PLAYER_FAILED_TABLE_IS_FULL));
    }


    // @Test
    // @Description("Verify adding player throws exception for the player, regaistered as judge")
    // public void shouldThrowExceptionWhenAddingPlayerRegisteredAsJudge(MafiaBotGameStatus exceptionGameStatus) {
    //     MafiaBotUser userToCreate = MafiaBotUser.builder()
    //                             .firstname("Test")
    //                             .lastname("User")
    //                             .location("Canning Town, London")
    //                             .nickname("Nickname")
    //                             .build();
    //     mafiaBotUserRepository.save(userToCreate);
    //     assertEquals("Wrong number of new users in repository",
    //                     mafiaBotUserRepository.findAll().size(), 1);
    //     MafiaBotUser user = mafiaBotUserRepository.findAll().get(0);
    //     MafiaBotGame gameToCreate = MafiaBotGame.builder()
    //                                     .gameStatus(exceptionGameStatus)
    //                                     .build();
    //     mafiaBotGameRepository.save(gameToCreate);
    //     assertEquals("Wrong number of new games in repository",
    //                     mafiaBotGameRepository.findAll().size(), 1);
    //     testEntityManager.detach(gameToCreate);
    //     MafiaBotGame game = mafiaBotGameRepository.findAll().get(0);
        
    //     Exception exception = assertThrows(MafiaBotAddPlayerException.class, () -> {
    //         mafiaBotService.addPlayer(game, user);
    //     });
        
    //     assertTrue("Wrong exception message for the exception, when game with wrong status is tried to be finished: " 
    //                 + exception.getMessage(),
    //         exception.getMessage().equals(MafiaBotService.ADD_PLAYER_FAILED_WRONG_GAME_STATUS_PLACEHOLDER + exceptionGameStatus.toString()));
    // }

    private MafiaBotGame createAndSaveMafiaBotGame(final MafiaBotGameStatus gameStatus) {
        MafiaBotGame game = createMafiaBotGame(gameStatus);
        mafiaBotGameRepository.saveAndFlush(game);
        return game;
    }

    private MafiaBotGame createMafiaBotGame(final MafiaBotGameStatus gameStatus) {
        MafiaBotGame game = MafiaBotGame.builder()
                                        .gameStatus(gameStatus)
                                        .build();
        return game;
    }

    private MafiaBotUser createAndSaveMafiaBotUser(final String telegramNickname) {
        MafiaBotUser user = createMafiaBotUser(telegramNickname);
        mafiaBotUserRepository.saveAndFlush(user);
        return user;
    }

    private MafiaBotUser createMafiaBotUser(final String telegramNickname) {
        MafiaBotUser user = MafiaBotUser.builder()
                                .telegramNickname(telegramNickname)
                                .firstname("Test")
                                .lastname("User")
                                .location("Canning Town, London")
                                .nickname("Nickname")
                                .mafiaPlayers(new HashSet<>())
                                .build();
        return user;
    }

    private MafiaBotPlayer createMafiaBotPlayer(MafiaBotGame game, MafiaBotUser user) {
        MafiaBotPlayer player = MafiaBotPlayer.builder()
                                .game(game)
                                .player(user)
                                .build();
        return player;
    }
}