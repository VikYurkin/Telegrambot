package ru.VYurkin.TelegramBot.services.interfaces;

import ru.VYurkin.TelegramBot.dto.GET.GroupDiscussionInfo.GroupDiscussionInfo;
import ru.VYurkin.TelegramBot.models.GroupSub;

import java.util.List;
import java.util.Optional;

public interface GroupSubService {
        GroupSub save(Long chatId, GroupDiscussionInfo groupDiscussionInfo);
        GroupSub save(GroupSub groupSub);
        Optional<GroupSub> findById(Integer id);
        List<GroupSub> findAll();
}
