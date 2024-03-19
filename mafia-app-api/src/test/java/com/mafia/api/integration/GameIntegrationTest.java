package com.mafia.api.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mafia.api.models.requests.NewGameRequest;
import com.mafia.api.utils.WebSecurityTestConfig;
import com.mafia.api.utils.WiremockSetup;

import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "30000")
@ContextConfiguration(initializers = {WiremockSetup.class})
// @ActiveProfiles({"development", "wiremock"})
@Import({ WebSecurityTestConfig.class })
public class GameIntegrationTest {
    @Autowired
    WebTestClient webTestClient;

    @Test
    void verifyGameByIdIsReturned() throws JsonProcessingException {
        EntityExchangeResult<byte[]> response = this.webTestClient
        .get()
        .uri(uriBuilder -> uriBuilder.path("/api/v1/game").build())
        .exchange()
        .expectBody().returnResult();

        final HttpStatusCode status = response.getStatus();
        assertEquals(HttpStatusCode.valueOf(200), status);
    }

        @Test
        void verifyGameIsCreated() throws JsonProcessingException {
            NewGameRequest requestBody = NewGameRequest.builder().build();

            EntityExchangeResult<byte[]> response = this.webTestClient
                .post()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/game").build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestBody), NewGameRequest.class)
                .exchange()
                .expectBody().returnResult();
    
            final HttpStatusCode status = response.getStatus();
            assertEquals(HttpStatusCode.valueOf(200), status);
        }
}
