package ru.VYurkin.TelegramBot.services;

import ru.VYurkin.TelegramBot.dto.GroupDiscussionInfo.GroupDiscussionInfo;
import ru.VYurkin.TelegramBot.models.GroupSub;

public interface GroupSubService {
        GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo);
}
