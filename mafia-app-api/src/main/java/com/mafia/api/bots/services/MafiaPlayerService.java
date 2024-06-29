package com.mafia.api.bots.services;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class MafiaPlayerService {

    
    private void sendMessage(String chatId, String messageText) {
        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
        message.setChatId(chatId);
        // message.enableMarkdown(true);
        // message.setReplyMarkup(getSettingsKeyboard(language));
        message.setText(messageText);
        // message.setReplyToMessageId(message.getMessageId());
        // message.setText(getSettingsMessage(update.getMessage().getText() + "qweasd"));
        
        // try {
            // execute(message); // Call method to send the message
        // } catch (TelegramApiException e) {
        //     e.printStackTrace();
        // }
    }
}
