package com.mafia.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MafiaApplication {
	public static void main(String[] args) {
		SpringApplication.run(MafiaApplication.class, args);
	}
}
