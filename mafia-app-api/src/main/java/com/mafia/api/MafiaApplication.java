package com.mafia.api;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.mafia.api.bots.MafiaPlayerBot;
import com.mafia.api.bots.repository.MafiaBotRepository;
import com.mafia.api.models.ClubLocation;
import com.mafia.api.models.GameParticipant;
import com.mafia.api.models.GameTable;
import com.mafia.api.models.MafiaClub;
import com.mafia.api.models.PlayerRole;
import com.mafia.api.models.player.Player;
import com.mafia.api.repository.ClubLocationRepository;
import com.mafia.api.repository.GameParticipantRepository;
import com.mafia.api.repository.GameRepository;
import com.mafia.api.repository.MafiaClubRepository;
import com.mafia.api.repository.PlayerRepository;

@SpringBootApplication
@EnableFeignClients
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class MafiaApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(MafiaApplication.class, args);
		 try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new MafiaPlayerBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
	}

	@Autowired
	MafiaClubRepository mafiaClubRepository;

	@Autowired
	ClubLocationRepository clubLocationRepository;

	@Autowired
	GameRepository gameRepository;

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	GameParticipantRepository gameParticipantRepository;

	@Override
	public void run(String... args) throws Exception {
		mafiaClubRepository.save(
			MafiaClub.builder()
			.name("London mafia club")
			.description("Test description")
			.build()
		);
		clubLocationRepository.save(
			ClubLocation.builder()
			.address("Stratford")
			.city("London")
			.country("UK")
			.build()
		);

		GameTable gameTableFirst = GameTable.builder()
		.bestMove("1,4,5")
		.gameTime(LocalDateTime.now())
		.judgeComments("Amazing civilian game")
		.playerComments("No questions to the game results")
		.build();

		gameRepository.save(gameTableFirst);

		Player firstPlayer = Player.builder()
			.firstname("Vladyslav")
			.lastname("Petryshyn")
			.location("London")
			.nickname("Adamant")
			.build();

		Player secondPlayer = Player.builder()
			.firstname("Elina")
			.lastname("Bartsevich")
			.location("London")
			.nickname("Ginger")
			.build();

		playerRepository.save(firstPlayer);
		playerRepository.save(secondPlayer);

		gameParticipantRepository.save(
			GameParticipant.builder()
			.gameTable(gameTableFirst)
			.player(firstPlayer)
			.build()
		);

	}

	@Bean
    public WebMvcConfigurer configure() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry reg) {
                reg.addMapping("/**").allowedOrigins("*");
            }
        };
    }
}
