package ru.VYurkin.TelegramBot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.VYurkin.TelegramBot.services.SendBotMessageService;

public class StartCommand implements Command{
    private final SendBotMessageService sendBotMessageService;

    public final static String START_MESSAGE ="Привет. Я помогу тебе быть в курсе последних " +
            "статей тех авторов, которые тебе интересны.";

    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}
