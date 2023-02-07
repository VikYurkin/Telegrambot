package ru.VYurkin.TelegramBot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.VYurkin.TelegramBot.command.CommandContainer;
import ru.VYurkin.TelegramBot.services.SendBotMessageServiceImpl;
import static ru.VYurkin.TelegramBot.command.CommandName.NO;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    public static String COMMAND_PREFIX = "/";
    @Value("${bot.username}")
    private String usernameBot;
    @Value("${bot.token}")
    private String tokenBot;

    private final CommandContainer commandContainer;

    public TelegramBot() {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this));
    }

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
            if(message.startsWith(COMMAND_PREFIX)){
                String commandIdentifier = message.split(" ")[0].toLowerCase();

                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }
    }
}

