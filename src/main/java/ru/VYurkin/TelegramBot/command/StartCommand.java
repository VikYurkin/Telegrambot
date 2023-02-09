package ru.VYurkin.TelegramBot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.VYurkin.TelegramBot.models.TelegramUser;
import ru.VYurkin.TelegramBot.services.SendBotMessageService;
import ru.VYurkin.TelegramBot.services.TelegramUserService;


public class StartCommand implements Command{
    private final SendBotMessageService sendBotMessageService;

    private final TelegramUserService telegramUserService;

    public final static String START_MESSAGE ="Привет. Я помогу тебе быть в курсе последних " +
            "статей тех авторов, которые тебе интересны.";

    public StartCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        telegramUserService.findByChatId(chatId).ifPresentOrElse(
                user-> {
                    user.setActive(true);
                    telegramUserService.save(user);
                },
                ()->{
                    TelegramUser telegramUser = new TelegramUser();
                    telegramUser.setActive(true);
                    telegramUser.setChartId(chatId);
                    telegramUserService.save(telegramUser);
                }
        );

        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}
