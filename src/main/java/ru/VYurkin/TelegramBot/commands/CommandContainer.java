package ru.VYurkin.TelegramBot.commands;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;
import ru.VYurkin.TelegramBot.clients.interfaces.GroupClient;
import ru.VYurkin.TelegramBot.commands.annotation.AdminCommand;
import ru.VYurkin.TelegramBot.commands.commands.*;
import ru.VYurkin.TelegramBot.services.interfaces.GroupSubService;
import ru.VYurkin.TelegramBot.services.interfaces.SendBotMessageService;
import ru.VYurkin.TelegramBot.services.interfaces.StatisticsService;
import ru.VYurkin.TelegramBot.services.interfaces.TelegramUserService;

import java.util.List;

import static java.util.Objects.nonNull;
import static ru.VYurkin.TelegramBot.commands.CommandName.*;


@Component
public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;
    private final GroupClient groupClient;
    private  final GroupSubService groupSubService;
    private final List<String> admins;
    private final StatisticsService statisticsService;

    public CommandContainer(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService, GroupClient groupClient, GroupSubService groupSubService, List<String> admins, StatisticsService statisticsService){
        this.groupClient = groupClient;
        this.groupSubService = groupSubService;
        this.admins = admins;
        this.statisticsService = statisticsService;

        commandMap = ImmutableMap.<String, Command> builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .put(STAT.getCommandName(), new StatCommand(sendBotMessageService, this.statisticsService))
                .put(ADD_GROUP_SUB.getCommandName(), new AddGroupSubCommand(sendBotMessageService, this.groupClient, this.groupSubService))
                .put(LIST_GROUP_SUB.getCommandName(), new ListGroupSubCommand(sendBotMessageService, telegramUserService))
                .put(DELETE_GROUP_SUB.getCommandName(), new DeleteGroupSubCommand(sendBotMessageService, telegramUserService, groupSubService))
                .put(ADMIN_HELP.getCommandName(), new AdminHelpCommand(sendBotMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command findCommand(String commandIdentifier, String username) {
        Command orDefault = commandMap.getOrDefault(commandIdentifier, unknownCommand);
        if (isAdminCommand(orDefault)) {
            if (admins.contains(username)) {
                return orDefault;
            } else {
                return unknownCommand;
            }
        }
        return orDefault;
    }
    private boolean isAdminCommand(Command command){
        return nonNull(command.getClass().getAnnotation(AdminCommand.class));
    }

}
