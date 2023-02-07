package ru.VYurkin.TelegramBot.command;

import static ru.VYurkin.TelegramBot.command.UnknownCommand.UNKNOWN_MESSAGE;

public class UnknownCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return "/unknownCommand";
    }

    @Override
    String getCommandMessage() {
        return UNKNOWN_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new UnknownCommand(sendBotMessageService);
    }
}
