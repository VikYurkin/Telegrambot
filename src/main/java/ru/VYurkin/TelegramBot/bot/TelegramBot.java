package ru.VYurkin.TelegramBot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.VYurkin.TelegramBot.client.GroupClient;
import ru.VYurkin.TelegramBot.command.CommandContainer;
import ru.VYurkin.TelegramBot.services.interfaces.GroupSubService;
import ru.VYurkin.TelegramBot.services.SendBotMessageServiceImpl;
import ru.VYurkin.TelegramBot.services.interfaces.StatisticsService;
import ru.VYurkin.TelegramBot.services.interfaces.TelegramUserService;

import java.util.List;

import static ru.VYurkin.TelegramBot.command.CommandName.NO;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    public static String COMMAND_PREFIX = "/";

    private final CommandContainer commandContainer;

    @Autowired
    public TelegramBot(TelegramUserService telegramUserService,
                       GroupClient groupClient,
                       GroupSubService groupSubService,
                       @Value("#{'${bot.admins}'.split(',')}") List<String> admins,
                       StatisticsService statisticsService) {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this), telegramUserService, groupClient, groupSubService, admins, statisticsService);
    }

    @Value("${bot.name}")
    private String usernameBot;
    @Value("${bot.token}")
    private String tokenBot;

    @Override
    public String getBotUsername() {
        return usernameBot;
    }

    @Override
    public String getBotToken() {
        return tokenBot;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            String message = update.getMessage().getText().trim();
            String username = update.getMessage().getFrom().getUserName();
            if(message.startsWith(COMMAND_PREFIX)){
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier, username).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName(), username).execute(update);
            }
        }
    }


    }

