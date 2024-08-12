package com.mafia.api.bots;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.mafia.api.bots.services.MafiaBotService;
import com.mafia.api.bots.services.MafiaPlayerService;

import lombok.RequiredArgsConstructor;

@SuppressWarnings("deprecation")
@Component
@RequiredArgsConstructor
public class MafiaPlayerBot extends TelegramLongPollingBot {
        private final MafiaPlayerService mafiaPlayerService;


        private static boolean gameStarted = false;
        private static final Set<String> ADMINS = Set.of("vladpetryshyn"); 
        private static final Set<String> ADMIN_COMMANDS = Set.of("/start", "/finish", "/remove", "/foul", "/techfoul", "/eliminate", "/addplayer");
        private static final Set<String> PLAYER_COMMANDS = Set.of( "/profile", "/askfoul", "/asktechfoul"); 
        private static String adminCommandCache;
        private static Map<Integer, String> playersByUsernames = new HashMap<Integer, String>();
        private static Map<Integer, Integer> playersWithFouls = new HashMap<Integer, Integer>();
        private static Map<Integer, Integer> playersWithTechnicalFouls = new HashMap<Integer, Integer>();
        // private static Set<Integer> places = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // private final MafiaBotRepository mafiaBotRepository;

        @Override
        public void onUpdateReceived(Update update) {
            if (update.hasMessage() && update.getMessage().hasText()) {
                String chatId = update.getMessage().getChatId().toString();
                String command = update.getMessage().getText();
                String username = update.getMessage().getFrom().getUserName();
                handleCommands(username, command, chatId);
            }
        }

        @Value("${telegram.bot.username}")
        private String botUsername;
    
        @Override
        public String getBotUsername() {
            return botUsername;
        }

        @Value("${telegram.bot.token}")
        private String botToken;
    
        @Override
        public String getBotToken() {
            return botToken;
        }

        private void handleCommands(final String username, final String command, final String chatId) {
            validateCommands(username, command, chatId);
            if (ADMINS.contains(username)) {
                mafiaPlayerService.handleAdminCommands(command, chatId);
            } else {
                handlePlayerCommands(username, command, chatId);
            }
        }

        private boolean validateCommands(final String username, final String command, final String chatId) {
            final boolean isAdmin = ADMINS.contains(username);
            final Set<String> commandsToValidate = isAdmin ? ADMIN_COMMANDS : PLAYER_COMMANDS;
            if (!commandsToValidate.contains(command) && (!isAdmin || adminCommandCache == null)) {
                sendMessage(chatId, "Invalid command");
                return false;
            }
            return true;
        }

        private void handlePlayerCommands(final String username, final String command, final String chatId) {

        }

        private void sendMessage(String chatId, String messageText) {
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(chatId);
            // message.enableMarkdown(true);
            // message.setReplyMarkup(getSettingsKeyboard(language));
            message.setText(messageText);
            // message.setReplyToMessageId(message.getMessageId());
            // message.setText(getSettingsMessage(update.getMessage().getText() + "qweasd"));
            
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        // private static ReplyKeyboardMarkup getSettingsKeyboard(String language) {
        //     ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        //     replyKeyboardMarkup.setSelective(true);
        //     replyKeyboardMarkup.setResizeKeyboard(true);
        //     replyKeyboardMarkup.setOneTimeKeyboard(false);

        //     List<KeyboardRow> keyboard = new ArrayList<>();
        //     KeyboardRow keyboardFirstRow = new KeyboardRow();
        //     keyboardFirstRow.add(getLanguagesCommand(language));
        //     keyboardFirstRow.add(getUnitsCommand(language));
        //     KeyboardRow keyboardSecondRow = new KeyboardRow();
        //     keyboardSecondRow.add(getAlertsCommand(language));
        //     keyboardSecondRow.add(getBackCommand(language));
        //     keyboard.add(keyboardFirstRow);
        //     keyboard.add(keyboardSecondRow);
        //     replyKeyboardMarkup.setKeyboard(keyboard);

        //     return replyKeyboardMarkup;
        // }

}
