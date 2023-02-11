package ru.VYurkin.TelegramBot.command;

import ru.VYurkin.TelegramBot.command.command.StatCommand;

import static ru.VYurkin.TelegramBot.command.CommandName.STAT;
import static ru.VYurkin.TelegramBot.command.command.StatCommand.STAT_MESSAGE;

public class StatCommandTest extends AbstractCommandTest {
    @Override
    String getCommandName() {
        return STAT.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return String.format(STAT_MESSAGE,0);
    }

    @Override
    Command getCommand() {
        return new StatCommand(sendBotMessageService, telegramUserService);
    }
}
