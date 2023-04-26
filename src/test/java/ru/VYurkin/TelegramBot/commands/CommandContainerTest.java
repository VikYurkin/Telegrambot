package ru.VYurkin.TelegramBot.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.VYurkin.TelegramBot.clients.interfaces.GroupClient;
import ru.VYurkin.TelegramBot.commands.Command;
import ru.VYurkin.TelegramBot.commands.CommandContainer;
import ru.VYurkin.TelegramBot.commands.CommandName;
import ru.VYurkin.TelegramBot.commands.commands.UnknownCommand;
import ru.VYurkin.TelegramBot.services.interfaces.GroupSubService;
import ru.VYurkin.TelegramBot.services.interfaces.SendBotMessageService;
import ru.VYurkin.TelegramBot.services.interfaces.StatisticsService;
import ru.VYurkin.TelegramBot.services.interfaces.TelegramUserService;

import java.util.Arrays;

import static java.util.Collections.singletonList;

@DisplayName("Unit-level testing for CommandContainer")
public class CommandContainerTest {
    private CommandContainer commandContainer;

    @BeforeEach
    public void init() {
        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        GroupClient groupClient = Mockito.mock(GroupClient.class);
        GroupSubService groupSubService = Mockito.mock(GroupSubService.class);
        StatisticsService statisticsService = Mockito.mock(StatisticsService.class);
        commandContainer = new CommandContainer(sendBotMessageService,
                telegramUserService,
                groupClient,
                groupSubService,
                singletonList("username"),
                statisticsService);
    }

    @Test
    public void shouldGetAllTheExistingCommands() {
        //when-then
        Arrays.stream(CommandName.values())
                .forEach(commandName -> {
                    Command command = commandContainer.findCommand(commandName.getCommandName(), "username");
                    Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
                });
    }

    @Test
    public void shouldReturnUnknownCommand() {
        //given
        String unknownCommand = "/fgjhdfgdfg";

        //when
        Command command = commandContainer.findCommand(unknownCommand, "username");

        //then
        Assertions.assertEquals(UnknownCommand.class, command.getClass());
    }
}


