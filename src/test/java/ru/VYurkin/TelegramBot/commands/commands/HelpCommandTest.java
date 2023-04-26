package ru.VYurkin.TelegramBot.commands.commands;

import org.junit.jupiter.api.DisplayName;
import ru.VYurkin.TelegramBot.commands.Command;

import static ru.VYurkin.TelegramBot.commands.CommandName.HELP;
import static ru.VYurkin.TelegramBot.commands.commands.HelpCommand.HELP_MESSAGE;

@DisplayName("Unit-level testing for HelpCommand")
public class HelpCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return HELP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return HELP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new HelpCommand(sendBotMessageService);
    }
}
