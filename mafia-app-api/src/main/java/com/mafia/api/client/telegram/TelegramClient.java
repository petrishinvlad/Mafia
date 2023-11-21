package com.mafia.api.client.telegram;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mafia.api.client.telegram.config.TelegramContextConfig;
import com.mafia.api.client.telegram.config.TelegramFeignWebClientConfigs;
import com.mafia.api.models.requests.MessengerPollRequest;

@FeignClient(
    value="telegram",
    url = "${telegram-api.url}",
    configuration = {TelegramContextConfig.class, TelegramFeignWebClientConfigs.class}
)
interface TelegramClient {
    @RequestMapping(
        method = RequestMethod.POST,
        value = "${telegram-api.send-poll-path}"
    )
    ResponseEntity<String> sendPoll(@SpringQueryMap MessengerPollRequest pollRequest);
}
