package ru.VYurkin.TelegramBot.clients;

import kong.unirest.GenericType;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.VYurkin.TelegramBot.clients.interfaces.PostClient;
import ru.VYurkin.TelegramBot.dto.POST.PostInfo.PostInfo;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostClientImpl implements PostClient {

    private final String apiPostPath;

    public PostClientImpl(@Value("${api.path}") String apiPostPath) {
        this.apiPostPath = apiPostPath+ "/posts";
    }

    @Override
    public List<PostInfo> findNewPosts(Integer groupId, Integer lastPostId) {
        List<PostInfo> lastPostsByGroup = Unirest.get(apiPostPath)
                .queryString("order", "NEW")
                .queryString("groupKid", groupId)
                .queryString("limit", 15)
                .asObject(new GenericType<List<PostInfo>>() {
                }).getBody();
        List<PostInfo> newPosts = new ArrayList<>();
        for (PostInfo post : lastPostsByGroup) {
            if (lastPostId>=post.getId())
                    break;

            newPosts.add(post);
        }
        return newPosts;
    }
}
