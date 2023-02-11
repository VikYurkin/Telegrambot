package ru.VYurkin.TelegramBot.client;

import kong.unirest.GenericType;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.VYurkin.TelegramBot.dto.GroupCountRequestArgs;
import ru.VYurkin.TelegramBot.dto.GroupDiscussionInfo.GroupDiscussionInfo;
import ru.VYurkin.TelegramBot.dto.GroupInfo.GroupInfo;
import ru.VYurkin.TelegramBot.dto.GroupRequestArgs;

import java.util.List;

@Component
public class GroupClientImpl implements GroupClient {

    private final String apiGroupPath;

    public GroupClientImpl(@Value("${api.path}") String apiGroupPath) {
        this.apiGroupPath = apiGroupPath+ "/groups";
    }


    @Override
    public List<GroupInfo> getGroupList(GroupRequestArgs requestArgs) {
        return Unirest.get(apiGroupPath)
                .queryString(requestArgs.populateQueries())
                .asObject(new GenericType<List<GroupInfo>>() {
                }).getBody();
    }

    @Override
    public List<GroupDiscussionInfo> getGroupDiscussionList(GroupRequestArgs requestArgs) {
        return Unirest.get(apiGroupPath)
                .queryString(requestArgs.populateQueries())
                .asObject(new GenericType<List<GroupDiscussionInfo>>() {
                }).getBody();
    }

    @Override
    public Integer getGroupCount(GroupCountRequestArgs countRequestArgs) {
        return Integer.valueOf(
                Unirest.get(String.format("%s/count", apiGroupPath))
                        .queryString(countRequestArgs.populateQueries())
                        .asString()
                        .getBody());
    }

    @Override
    public GroupDiscussionInfo getGroupById(Integer id) {
        return Unirest.get(String.format("%s/group%s", apiGroupPath, id.toString()))
                .asObject(GroupDiscussionInfo.class)
                .getBody();
    }

}
