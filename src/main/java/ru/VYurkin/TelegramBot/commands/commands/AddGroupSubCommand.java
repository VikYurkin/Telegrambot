package ru.VYurkin.TelegramBot.commands.commands;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.VYurkin.TelegramBot.clients.interfaces.GroupClient;
import ru.VYurkin.TelegramBot.commands.Command;
import ru.VYurkin.TelegramBot.dto.GET.GroupDiscussionInfo.GroupDiscussionInfo;
import ru.VYurkin.TelegramBot.dto.GET.GroupRequestArgs;
import ru.VYurkin.TelegramBot.models.GroupSub;
import ru.VYurkin.TelegramBot.services.interfaces.GroupSubService;
import ru.VYurkin.TelegramBot.services.interfaces.SendBotMessageService;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import static ru.VYurkin.TelegramBot.commands.CommandName.ADD_GROUP_SUB;

public class AddGroupSubCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final GroupClient groupClient;
    private final GroupSubService groupSubService;

    public AddGroupSubCommand(SendBotMessageService sendBotMessageService, GroupClient groupClient, GroupSubService groupSubService) {
        this.sendBotMessageService = sendBotMessageService;
        this.groupClient = groupClient;
        this.groupSubService = groupSubService;
    }


    @Override
    public void execute(Update update) {
        if (update.getMessage().getText().trim().equalsIgnoreCase(ADD_GROUP_SUB.getCommandName())) {
            sendGroupIdList(update.getMessage().getChatId());
            return;
        }
        String groupId = update.getMessage().getText().trim().split(SPACE)[1];
        Long chatId = update.getMessage().getChatId();
        if (isNumeric(groupId)) {
            GroupDiscussionInfo groupById = groupClient.getGroupById(Integer.parseInt(groupId));
            if (isNull(groupById.getId())) {
                sendGroupNotFound(chatId, groupId);
            }
            GroupSub savedGroupSub = groupSubService.save(chatId, groupById);
            sendBotMessageService.sendMessage(chatId, "Подписал на группу " + savedGroupSub.getTitle());
        } else {
            sendGroupNotFound(chatId, groupId);
        }
    }

    private void sendGroupNotFound(Long chatId, String groupId) {
        String groupNotFoundMessage = "Нет группы с ID = \"%s\"";
        sendBotMessageService.sendMessage(chatId, String.format(groupNotFoundMessage, groupId));
    }

    private void sendGroupIdList(Long chatId) {

        StringBuilder groupIds = new StringBuilder();

        groupIds.append("Чтобы подписаться на группу - передай команду вместе с ID группы. \n" +
                "Например: /addGroupSub 16. \n\n" +
                "Я подготовил список всех групп - выбирай какую хочешь :) \n\n" +
                "Имя группы - ID группы \n\n");

        groupClient.getGroupList(GroupRequestArgs.builder().build())
                .forEach(group -> groupIds
                                          .append(group.getTitle())
                                          .append(" -  ")
                                          .append(group.getId())
                                          .append("\n"));

        sendBotMessageService.sendMessage(chatId, groupIds.toString());
    }
}
