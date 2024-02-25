package com.mafia.api.client.polemica;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mafia.api.client.polemica.models.PolemicaGameStats;

@FeignClient(
    value="polemi",
    url = "https://polemicagames.kz",
    configuration={PolemicaContextConfig.class}
)
public interface PolemicaClient {
    @RequestMapping(
        method=RequestMethod.POST,
        value="/game-statistics/{gameid}")
    ResponseEntity<PolemicaGameStats> polemicaGameStats(
        @PathVariable String gameid
    );
}
