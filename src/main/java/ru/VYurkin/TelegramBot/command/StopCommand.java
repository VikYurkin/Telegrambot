package ru.VYurkin.TelegramBot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.VYurkin.TelegramBot.services.SendBotMessageService;

public class StopCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public static final String STOP_MESSAGE ="Деактивировал все ваши подписки \uD83D\uDE1F.";

    public StopCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), STOP_MESSAGE);
    }
}
