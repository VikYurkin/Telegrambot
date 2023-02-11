package ru.VYurkin.TelegramBot.services;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ru.VYurkin.TelegramBot.bot.TelegramBot;
import ru.VYurkin.TelegramBot.services.interfaces.SendBotMessageService;

import java.util.List;

@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {

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

    @Override
    public void sendMessage(String chatId, List<String> messages) {
        if(CollectionUtils.isEmpty(messages))
            return;

        messages.forEach(message->sendMessage(chatId,message));
    }
}
