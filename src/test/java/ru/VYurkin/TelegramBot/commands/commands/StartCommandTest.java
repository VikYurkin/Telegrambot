package ru.VYurkin.TelegramBot.commands.commands;

import org.junit.jupiter.api.DisplayName;
import ru.VYurkin.TelegramBot.commands.Command;

import static ru.VYurkin.TelegramBot.commands.CommandName.START;
import static ru.VYurkin.TelegramBot.commands.commands.StartCommand.START_MESSAGE;


@DisplayName("Unit-level testing for StartCommand")
public class StartCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return START.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return START_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StartCommand(sendBotMessageService, telegramUserService);
    }
}
