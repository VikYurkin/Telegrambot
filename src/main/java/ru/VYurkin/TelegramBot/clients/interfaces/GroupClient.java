package ru.VYurkin.TelegramBot.clients.interfaces;

import ru.VYurkin.TelegramBot.dto.GET.GroupDiscussionInfo.GroupDiscussionInfo;
import ru.VYurkin.TelegramBot.dto.GET.GroupInfo.GroupInfo;
import ru.VYurkin.TelegramBot.dto.GET.GroupRequestArgs;
import java.util.List;

public interface GroupClient {

    List<GroupInfo> getGroupList(GroupRequestArgs requestArgs);

    List<GroupDiscussionInfo> getGroupDiscussionList(GroupRequestArgs requestArgs);

    Integer getGroupCount(GroupRequestArgs countRequestArgs);


    GroupDiscussionInfo getGroupById(Integer id);

    Integer findLastPostId(Integer groupSubId);
}