package ru.VYurkin.TelegramBot.commands.commands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.VYurkin.TelegramBot.commands.commands.ListGroupSubCommand;
import ru.VYurkin.TelegramBot.models.GroupSub;
import ru.VYurkin.TelegramBot.models.TelegramUser;
import ru.VYurkin.TelegramBot.services.interfaces.SendBotMessageService;
import ru.VYurkin.TelegramBot.services.interfaces.TelegramUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.VYurkin.TelegramBot.commands.CommandName.LIST_GROUP_SUB;

@DisplayName("Unit-level testing for ListGroupSubCommand")
public class ListGroupSubCommandTest {

    @Test
    public void shouldProperlyShowsListGroupSub() {
        //given
        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setActive(true);
        telegramUser.setChartId(1L);

        List<GroupSub> groupSubList = new ArrayList<>();
        groupSubList.add(populateGroupSub(1, "gs1"));
        groupSubList.add(populateGroupSub(2, "gs2"));
        groupSubList.add(populateGroupSub(3, "gs3"));
        groupSubList.add(populateGroupSub(4, "gs4"));

        telegramUser.setGroupSubs(groupSubList);

        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);

        Mockito.when(telegramUserService.findByChatId(telegramUser.getChartId())).thenReturn(Optional.of(telegramUser));

        ListGroupSubCommand command = new ListGroupSubCommand(sendBotMessageService, telegramUserService);

        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(telegramUser.getChartId());
        Mockito.when(message.getText()).thenReturn(LIST_GROUP_SUB.getCommandName());
        update.setMessage(message);

        StringBuilder collectedGroups =new StringBuilder("Я нашел все подписки на группы: \n\n");

                telegramUser.getGroupSubs()
                        .forEach(it -> collectedGroups.append("Группа: ")
                                                      .append(it.getTitle())
                                                      .append(", ID = ")
                                                      .append(it.getId())
                                                      .append(" \n"));
        //when
        command.execute(update);


        //then
        Mockito.verify(sendBotMessageService).sendMessage(telegramUser.getChartId(), collectedGroups.toString());
    }

    private GroupSub populateGroupSub(Integer id, String title) {
        GroupSub gs = new GroupSub();
        gs.setId(id);
        gs.setTitle(title);
        return gs;
    }
}
