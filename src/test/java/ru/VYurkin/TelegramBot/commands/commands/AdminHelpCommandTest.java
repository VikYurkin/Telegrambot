package ru.VYurkin.TelegramBot.commands.commands;

import org.junit.jupiter.api.DisplayName;
import ru.VYurkin.TelegramBot.commands.Command;

import static ru.VYurkin.TelegramBot.commands.CommandName.ADMIN_HELP;
import static ru.VYurkin.TelegramBot.commands.commands.AdminHelpCommand.ADMIN_HELP_MESSAGE;

@DisplayName("Unit-level testing for AdminHelpCommand")
public class AdminHelpCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return ADMIN_HELP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return ADMIN_HELP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new AdminHelpCommand(sendBotMessageService);
    }

}
