package ru.VYurkin.TelegramBot.services.interfaces;

import java.util.List;

public interface SendBotMessageService {
    void sendMessage(Long chatId, String message);
    void sendMessage(Long chatId, List<String> messages);
}
