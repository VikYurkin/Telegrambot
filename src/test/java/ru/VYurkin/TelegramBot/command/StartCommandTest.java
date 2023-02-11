package ru.VYurkin.TelegramBot.command;

import org.junit.jupiter.api.DisplayName;
import ru.VYurkin.TelegramBot.command.command.StartCommand;

import static ru.VYurkin.TelegramBot.command.CommandName.START;
import static ru.VYurkin.TelegramBot.command.command.StartCommand.START_MESSAGE;

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
