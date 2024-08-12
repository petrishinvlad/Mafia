package com.mafia.api.bots.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MafiaPlayerService {
    private final MafiaBotService mafiaBotRepository;

    private static boolean gameStarted = false;
    private static final Set<String> ADMINS = Set.of("vladpetryshyn"); 
    private static final Set<String> ADMIN_COMMANDS = Set.of("/start", "/finish", "/remove", "/foul", "/techfoul", "/eliminate", "/addplayer");
    private static final Set<String> PLAYER_COMMANDS = Set.of( "/profile", "/askfoul", "/asktechfoul"); 
    private static String adminCommandCache;
    private static Map<Integer, String> playersByUsernames = new HashMap<Integer, String>();
    private static Map<Integer, Integer> playersWithFouls = new HashMap<Integer, Integer>();
    private static Map<Integer, Integer> playersWithTechnicalFouls = new HashMap<Integer, Integer>();

    public void handleAdminCommands(final String command, final String chatId) {
        switch (command) {
            case "/start":
                playersByUsernames.clear();
                gameStarted = true;
                adminCommandCache = null;
                sendMessage(chatId, "Game started");
                break;
            case "/finish": 
                playersByUsernames.clear();
                gameStarted = false;
                adminCommandCache = null;
                sendMessage(chatId, "Game finished");
                break;
            case "/remove":
                sendMessage(chatId, "Which player would you like to remove?");
                adminCommandCache = "/remove";
                break;
            case "/foul":
                sendMessage(chatId, "Which player would you like to add foul for?");
                adminCommandCache = "/foul";
                break;
            case "/techfoul":
                sendMessage(chatId, "Which player would you like to add tech foul for?");
                adminCommandCache = "/techfoul";
                break;
            case "/eliminate":
                sendMessage(chatId, "Which player would you like to eliminate?");
                adminCommandCache = "/eliminate";
                break;
            case "/status":
                sendMessage(chatId, "Which player stats would you like to see?");
                adminCommandCache = "/status";
                break;
            case "/addplayer":
                sendMessage(chatId, "Which player would you like to add?");
                adminCommandCache = "/addplayer";
                break;
            case "/clear":
                adminCommandCache = null;
                sendMessage(chatId, "Cache is cleared");
                break;
            default:
                if (adminCommandCache != null) {
                    executeAdminCommand(chatId, command);
                }
                break;
        }
    }

    private void executeAdminCommand(final String chatId, final String command) {
        switch (adminCommandCache) {
            case "/remove":
                try {
                    Integer playerSeat = Integer.valueOf(command);
                    String playerUsername = playersByUsernames.get(playerSeat);
                    playersByUsernames.remove(playerSeat);
                    sendMessage(chatId, "Player " + playerUsername +  " is removed");
                } catch (Exception e) {
                    sendMessage(chatId, "Player could not be removed");
                }
                break;
            case "/foul":
                try {
                    Integer playerSeat = Integer.valueOf(command);
                    String playerUsername = playersByUsernames.get(playerSeat);
                    playersWithFouls.put(playerSeat, playersWithFouls.getOrDefault(playerSeat, 0) + 1);
                    sendMessage(chatId, "Player " + playerUsername +  " received foul");
                } catch (Exception e) {
                    sendMessage(chatId, "Cannot add foul for the player");
                }
                break;
            case "/techfoul":
                try {
                    Integer playerSeat = Integer.valueOf(command);
                    String playerUsername = playersByUsernames.get(playerSeat);
                    playersWithTechnicalFouls.put(playerSeat, playersWithTechnicalFouls.getOrDefault(playerSeat, 0) + 1);
                    sendMessage(chatId, "Player " + playerUsername +  " received technical foul");
                } catch (Exception e) {
                    sendMessage(chatId, "Cannot add technical foul for the player");
                }
                break;
            // case "eliminate":
            //     sendMessage(chatId, "Which player would you like to eliminate?");
            //     break;
            case "/status":
                String statusMessage = "";
                try {
                    Integer playerSeat = Integer.valueOf(command);
                    String playerUsername = playersByUsernames.get(playerSeat);
                    statusMessage += "Username: " + playerUsername + "\n";
                    statusMessage += "Seat: " + playerSeat + "\n";
                    statusMessage += "Fouls: " + playersWithFouls.getOrDefault(playerSeat, 0) + "\n";
                    statusMessage += "Technical fouls: " + playersWithTechnicalFouls.getOrDefault(playerSeat, 0) + "\n";
                    playersWithTechnicalFouls.put(playerSeat, playersWithTechnicalFouls.getOrDefault(playerSeat, 0) + 1);
                    sendMessage(chatId, statusMessage);
                } catch (Exception e) {
                    sendMessage(chatId, "Cannot provide status for the player");
                }
                break;
            case "/addplayer":
                Set<Integer> availablePlaces = new HashSet<Integer>();
                Random rn = new Random();
                Integer place = null;
                for (int i = 1; i <= 10; ++i) {
                    if (!playersByUsernames.containsKey(i)) {
                        availablePlaces.add(i);
                    }
                }
                int randomIndex = new Random().nextInt(10);//0..9
                int i = 0;
                for (Integer element : availablePlaces) {
                    if (i == randomIndex) {
                        place = element;
                    }
                    i++;
                }
                if (place != null) {
                    try {
                        playersByUsernames.put(place, command);
                        sendMessage(chatId, "Player has been added on seat " + place);
                    } catch (Exception e) {
                        sendMessage(chatId, "Cannot update the board");
                    }
                } else {
                    sendMessage(chatId, "No free space for the player");
                }
                adminCommandCache = null;
                break;
            case "clear":
                adminCommandCache = null;
                break;
            default:
                adminCommandCache = null;
                break;
        }
    }

    
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
