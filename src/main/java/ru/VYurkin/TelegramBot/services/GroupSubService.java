package ru.VYurkin.TelegramBot.services;

import ru.VYurkin.TelegramBot.dto.GroupDiscussionInfo.GroupDiscussionInfo;
import ru.VYurkin.TelegramBot.models.GroupSub;

import java.util.Optional;

public interface GroupSubService {
        GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo);
        GroupSub save(GroupSub groupSub);
        Optional<GroupSub> findById(Integer id);
}
