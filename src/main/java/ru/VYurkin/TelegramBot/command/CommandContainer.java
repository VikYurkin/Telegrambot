package ru.VYurkin.TelegramBot.command;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;
import ru.VYurkin.TelegramBot.services.SendBotMessageService;
import static ru.VYurkin.TelegramBot.command.CommandName.*;


@Component
public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService){

        commandMap = ImmutableMap.<String, Command> builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

}
