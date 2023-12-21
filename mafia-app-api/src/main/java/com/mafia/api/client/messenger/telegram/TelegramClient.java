package com.mafia.api.client.messenger.telegram;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mafia.api.client.messenger.telegram.config.TelegramContextConfig;
import com.mafia.api.client.messenger.telegram.config.TelegramFeignWebClientConfigs;
import com.mafia.api.models.requests.MessengerPollRequest;

// @FeignClient(
//     value="telegram",
//     url = "${telegram-api.url}",
//     configuration = {TelegramContextConfig.class, TelegramFeignWebClientConfigs.class}
// )

@FeignClient(
    value="telegram",
    url = "https://httpbin.org"
)
interface TelegramClient {
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/get"
    )
    ResponseEntity<String> sendPoll();
}
