package ru.VYurkin.TelegramBot.command;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;
import ru.VYurkin.TelegramBot.client.GroupClient;
import ru.VYurkin.TelegramBot.command.command.*;
import ru.VYurkin.TelegramBot.services.GroupSubService;
import ru.VYurkin.TelegramBot.services.SendBotMessageService;
import ru.VYurkin.TelegramBot.services.TelegramUserService;

import static ru.VYurkin.TelegramBot.command.CommandName.*;


@Component
public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;
    private final GroupClient groupClient;
    private  final GroupSubService groupSubService;

    public CommandContainer(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService, GroupClient groupClient, GroupSubService groupSubService){
        this.groupClient = groupClient;
        this.groupSubService = groupSubService;

        commandMap = ImmutableMap.<String, Command> builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .put(STAT.getCommandName(), new StatCommand(sendBotMessageService, telegramUserService))
                .put(ADD_GROUP_SUB.getCommandName(), new AddGroupSubCommand(sendBotMessageService, this.groupClient, this.groupSubService))
                .put(LIST_GROUP_SUB.getCommandName(), new ListGroupSubCommand(sendBotMessageService, telegramUserService))
                .put(DELETE_GROUP_SUB.getCommandName(), new DeleteGroupSubCommand(sendBotMessageService, telegramUserService, groupSubService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

}
