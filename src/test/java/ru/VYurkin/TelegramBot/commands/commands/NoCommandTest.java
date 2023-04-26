package ru.VYurkin.TelegramBot.commands.commands;

import org.junit.jupiter.api.DisplayName;
import ru.VYurkin.TelegramBot.commands.Command;

import static ru.VYurkin.TelegramBot.commands.CommandName.NO;
import static ru.VYurkin.TelegramBot.commands.commands.NoCommand.NO_MESSAGE;

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
