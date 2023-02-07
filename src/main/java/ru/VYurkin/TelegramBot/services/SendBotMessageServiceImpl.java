package ru.VYurkin.TelegramBot.services;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ru.VYurkin.TelegramBot.bot.TelegramBot;

@Service
public class SendBotMessageServiceImpl implements SendBotMessageService{

    private final TelegramBot telegramBot;

    public SendBotMessageServiceImpl(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        try {
                telegramBot.execute(sendMessage);
            }catch (TelegramApiRequestException e) {
                e.printStackTrace();
            } catch (TelegramApiException e) {
    }

    }
}
