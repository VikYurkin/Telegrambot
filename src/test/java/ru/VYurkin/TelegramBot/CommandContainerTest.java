package ru.VYurkin.TelegramBot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.VYurkin.TelegramBot.client.GroupClient;
import ru.VYurkin.TelegramBot.command.Command;
import ru.VYurkin.TelegramBot.command.CommandContainer;
import ru.VYurkin.TelegramBot.command.CommandName;
import ru.VYurkin.TelegramBot.command.command.UnknownCommand;
import ru.VYurkin.TelegramBot.services.GroupSubService;
import ru.VYurkin.TelegramBot.services.SendBotMessageService;
import ru.VYurkin.TelegramBot.services.TelegramUserService;

import java.util.Arrays;

@DisplayName("Unit-level testing for CommandContainer")
public class CommandContainerTest {

    private CommandContainer commandContainer;
    private GroupClient groupClient;
    private GroupSubService groupSubService;



    @BeforeEach
    public void init() {
        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        commandContainer = new CommandContainer(sendBotMessageService,telegramUserService, groupClient, groupSubService);
    }

    @Test
    public void shouldGetAllTheExistingCommands() {
        //when-then
        Arrays.stream(CommandName.values())
                .forEach(commandName -> {
                    Command command = commandContainer.retrieveCommand(commandName.getCommandName());
                    Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
                });
    }

    @Test
    public void shouldReturnUnknownCommand() {
        //given
        String unknownCommand = "/unknownCommand";

        //when
        Command command = commandContainer.retrieveCommand(unknownCommand);

        //then
        Assertions.assertEquals(UnknownCommand.class, command.getClass());
    }
}


