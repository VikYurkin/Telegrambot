package ru.VYurkin.TelegramBot.commands.commands;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.VYurkin.TelegramBot.commands.Command;
import ru.VYurkin.TelegramBot.services.interfaces.SendBotMessageService;

import static ru.VYurkin.TelegramBot.commands.CommandName.STAT;

public class AdminHelpCommand implements Command {

    public static final String ADMIN_HELP_MESSAGE = String.format("✨<b>Доступные команды админа</b>✨\n\n"
                    + "<b>Получить статистику</b>\n"
                    + "%s - статистика бота\n",
            STAT.getCommandName());

    private final SendBotMessageService sendBotMessageService;

    public AdminHelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId(), ADMIN_HELP_MESSAGE);
    }
}
