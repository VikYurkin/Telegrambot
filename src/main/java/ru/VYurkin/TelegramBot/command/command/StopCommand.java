package ru.VYurkin.TelegramBot.command.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.VYurkin.TelegramBot.command.Command;
import ru.VYurkin.TelegramBot.services.interfaces.SendBotMessageService;
import ru.VYurkin.TelegramBot.services.interfaces.TelegramUserService;

public class StopCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    private final TelegramUserService telegramUserService;

    public static final String STOP_MESSAGE ="Деактивировал все ваши подписки \uD83D\uDE1F.";

    public StopCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), STOP_MESSAGE);

        String chatId = update.getMessage().getChatId().toString();
        telegramUserService.findByChatId(chatId).ifPresent(
                user-> {
                    user.setActive(false);
                    telegramUserService.save(user);
                }
        );
    }
}
