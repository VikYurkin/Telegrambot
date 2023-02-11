package ru.VYurkin.TelegramBot.client;

import ru.VYurkin.TelegramBot.dto.GET.GroupCountRequestArgs;
import ru.VYurkin.TelegramBot.dto.GET.GroupDiscussionInfo.GroupDiscussionInfo;
import ru.VYurkin.TelegramBot.dto.GET.GroupInfo.GroupInfo;
import ru.VYurkin.TelegramBot.dto.GET.GroupRequestArgs;

import java.util.List;

public interface GroupClient {

    List<GroupInfo> getGroupList(GroupRequestArgs requestArgs);

    List<GroupDiscussionInfo> getGroupDiscussionList(GroupRequestArgs requestArgs);

    Integer getGroupCount(GroupCountRequestArgs countRequestArgs);


    GroupDiscussionInfo getGroupById(Integer id);

    Integer findLastArticleId(Integer groupSubId);
}