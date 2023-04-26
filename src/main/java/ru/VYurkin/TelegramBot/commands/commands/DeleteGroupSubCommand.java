package ru.VYurkin.TelegramBot.commands.commands;

import jakarta.ws.rs.NotFoundException;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.VYurkin.TelegramBot.commands.Command;
import ru.VYurkin.TelegramBot.models.GroupSub;
import ru.VYurkin.TelegramBot.models.TelegramUser;
import ru.VYurkin.TelegramBot.services.interfaces.GroupSubService;
import ru.VYurkin.TelegramBot.services.interfaces.SendBotMessageService;
import ru.VYurkin.TelegramBot.services.interfaces.TelegramUserService;
import java.util.List;
import java.util.Optional;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import static ru.VYurkin.TelegramBot.commands.CommandName.DELETE_GROUP_SUB;

public class DeleteGroupSubCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;
    private final GroupSubService groupSubService;

    public DeleteGroupSubCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService, GroupSubService groupSubService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
        this.groupSubService = groupSubService;
    }

    @Override
    public void execute(Update update) {
        if (update.getMessage().getText().trim().equalsIgnoreCase(DELETE_GROUP_SUB.getCommandName())) {
            sendGroupIdList(update.getMessage().getChatId());
            return;
        }
        String groupId = update.getMessage().getText().trim().split(SPACE)[1];
        Long chatId = update.getMessage().getChatId();
        if (isNumeric(groupId)) {
            Optional<GroupSub> optionalGroupSub = groupSubService.findById(Integer.valueOf(groupId));
            if (optionalGroupSub.isPresent()) {
                GroupSub groupSub = optionalGroupSub.get();
                TelegramUser telegramUser = telegramUserService.findByChatId(chatId).orElseThrow(NotFoundException::new);
                groupSub.getUsers().remove(telegramUser);
                groupSubService.save(groupSub);
                sendBotMessageService.sendMessage(chatId, String.format("Удалил подписку на группу: %s", groupSub.getTitle()));
            } else {
                sendBotMessageService.sendMessage(chatId, "Не нашел такой группы =/");
            }
        } else {
            sendBotMessageService.sendMessage(chatId, "Неправильный формат ID группы.\n " +
                    "ID должно быть целым положительным числом");
        }
    }

    private void sendGroupIdList(Long chatId) {
        StringBuilder message = new StringBuilder();
        List<GroupSub> groupSubs = telegramUserService.findByChatId(chatId)
                .orElseThrow(NotFoundException::new)
                .getGroupSubs();
        if (CollectionUtils.isEmpty(groupSubs)) {
            message.append("Пока нет подписок на группы. Чтобы добавить подписку напиши /addGroupSub");
        } else {
             message.append ("""
                     Чтобы удалить подписку на группу - передай команду вместе с ID группы.\s
                     Например: /deleteGroupSub 16\s

                     Я подготовил список всех групп, на которые ты подписан)\s

                     Имя группы - ID группы\s

                     """);

        groupSubs.forEach(group -> message.append(group.getTitle())
                                          .append(" - ")
                                          .append(group.getId())
                                          .append("\n"));
        }
        sendBotMessageService.sendMessage(chatId, message.toString());
    }
}
