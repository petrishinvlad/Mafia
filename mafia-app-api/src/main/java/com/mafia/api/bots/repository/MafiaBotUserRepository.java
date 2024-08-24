package com.mafia.api.bots.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mafia.api.bots.models.MafiaBotUser;
import java.util.List;


@Repository
public interface MafiaBotUserRepository extends JpaRepository<MafiaBotUser, Long> {
    List<MafiaBotUser> findByTelegramNickname(String telegramNickname);
}