package ru.VYurkin.TelegramBot.commands.commands;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.VYurkin.TelegramBot.commands.Command;
import ru.VYurkin.TelegramBot.models.TelegramUser;
import ru.VYurkin.TelegramBot.services.interfaces.SendBotMessageService;
import ru.VYurkin.TelegramBot.services.interfaces.TelegramUserService;

public class ListGroupSubCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public ListGroupSubCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        TelegramUser telegramUser = telegramUserService
                .findByChatId(update.getMessage().getChatId())
                .orElseThrow(NoSuchFieldError::new);

        StringBuilder message =new StringBuilder("Я нашел все подписки на группы: \n\n");

        if(telegramUser.getGroupSubs().isEmpty())
            message.append("...а их не оказалось.\n Подписывайся на группы статей в JavaRush по команде /addGroupSub");

        telegramUser.getGroupSubs().forEach(it -> message.append("Группа: ")
                                                         .append(it.getTitle())
                                                         .append(", ID = ")
                                                         .append(it.getId())
                                                         .append(" \n"));

        sendBotMessageService.sendMessage(telegramUser.getChartId(), message.toString());
    }
}
