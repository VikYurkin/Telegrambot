package ru.VYurkin.TelegramBot.client;

import ru.VYurkin.TelegramBot.dto.GroupCountRequestArgs;
import ru.VYurkin.TelegramBot.dto.GroupDiscussionInfo.GroupDiscussionInfo;
import ru.VYurkin.TelegramBot.dto.GroupInfo.GroupInfo;
import ru.VYurkin.TelegramBot.dto.GroupRequestArgs;

import java.util.List;

public interface GroupClient {

    List<GroupInfo> getGroupList(GroupRequestArgs requestArgs);


    List<GroupDiscussionInfo> getGroupDiscussionList(GroupRequestArgs requestArgs);



    Integer getGroupCount(GroupCountRequestArgs countRequestArgs);


    GroupDiscussionInfo getGroupById(Integer id);
}