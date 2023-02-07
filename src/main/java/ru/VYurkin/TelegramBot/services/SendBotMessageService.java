package ru.VYurkin.TelegramBot.services;

public interface SendBotMessageService {
    void sendMessage(String chatId, String message);
}
