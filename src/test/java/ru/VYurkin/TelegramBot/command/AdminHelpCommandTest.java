package ru.VYurkin.TelegramBot.command;

import org.junit.jupiter.api.DisplayName;
import ru.VYurkin.TelegramBot.command.command.AdminHelpCommand;

import static ru.VYurkin.TelegramBot.command.CommandName.ADMIN_HELP;
import static ru.VYurkin.TelegramBot.command.command.AdminHelpCommand.ADMIN_HELP_MESSAGE;

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
