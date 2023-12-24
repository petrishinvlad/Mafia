package com.mafia.api;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

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

		playerRepository.save(Player.builder()
			.firstname("Vladyslav")
			.lastname("Petryshyn")
			.location("London")
			.nickname("Adamant")
			.build()
		);

		gameParticipantRepository.save(
			GameParticipant.builder()
			.gameTable(gameTableFirst)
			.role(PlayerRole.SHERIFF)
			.build()
		);

	}
}
