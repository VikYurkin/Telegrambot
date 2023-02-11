package ru.VYurkin.TelegramBot.command;

import org.junit.jupiter.api.DisplayName;
import ru.VYurkin.TelegramBot.command.command.NoCommand;

import static ru.VYurkin.TelegramBot.command.CommandName.NO;
import static ru.VYurkin.TelegramBot.command.command.NoCommand.NO_MESSAGE;

@DisplayName("Unit-level testing for NoCommand")
public class NoCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return NO.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return NO_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new NoCommand(sendBotMessageService);
    }
}
