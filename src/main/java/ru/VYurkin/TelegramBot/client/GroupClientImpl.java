package ru.VYurkin.TelegramBot.client;

import kong.unirest.GenericType;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.VYurkin.TelegramBot.dto.GET.GroupCountRequestArgs;
import ru.VYurkin.TelegramBot.dto.GET.GroupDiscussionInfo.GroupDiscussionInfo;
import ru.VYurkin.TelegramBot.dto.GET.GroupInfo.GroupInfo;
import ru.VYurkin.TelegramBot.dto.GET.GroupRequestArgs;
import ru.VYurkin.TelegramBot.dto.POST.PostInfo.PostInfo;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.CollectionUtils.isEmpty;

@Component
public class GroupClientImpl implements GroupClient {

    private final String apiGroupPath;
    private final String apiPostPath;

    public GroupClientImpl(@Value("${api.path}") String apiGroupPath) {
        this.apiGroupPath = apiGroupPath+ "/groups";
        this.apiPostPath = apiGroupPath+"/posts";
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

    @Override
    public Integer findLastArticleId(Integer groupSubId) {
        List<PostInfo> posts = Unirest.get(apiPostPath)
                .queryString("order", "NEW")
                .queryString("groupKid", groupSubId.toString())
                .queryString("limit", "1")
                .asObject(new GenericType<List<PostInfo>>() {
                })
                .getBody();
        return isEmpty(posts) ? 0 : Optional.ofNullable(posts.get(0)).map(PostInfo::getId).orElse(0);
    }

}
