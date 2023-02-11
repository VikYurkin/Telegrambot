package ru.VYurkin.TelegramBot.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.VYurkin.TelegramBot.client.PostClient;
import ru.VYurkin.TelegramBot.dto.POST.PostInfo.PostInfo;
import ru.VYurkin.TelegramBot.models.GroupSub;
import ru.VYurkin.TelegramBot.models.TelegramUser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FindNewArticleServiceImp implements FindNewArticleService {
    private final String apiGroupPath;
    private final GroupSubService groupSubService;
    private final PostClient postClient;
    private final SendBotMessageService sendMessageService;

    public FindNewArticleServiceImp(@Value("${api.path}")String apiGroupPath,
                                    GroupSubService groupSubService,
                                    PostClient postClient,
                                    SendBotMessageService sendMessageService) {
        this.apiGroupPath = apiGroupPath+ "/posts%s";
        this.groupSubService = groupSubService;
        this.postClient = postClient;
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void findNewArticles() {
        groupSubService.findAll().forEach(gSub->{
            List<PostInfo> newPosts = postClient.findNewPosts(gSub.getId(), gSub.getLastArticleId());
            setNewLastArticleId(gSub, newPosts);
            notifySubscribersAboutNewArticles(gSub, newPosts);
        });
    }

    private void notifySubscribersAboutNewArticles(GroupSub gSub, List<PostInfo> newPosts) {
        Collections.reverse(newPosts);
        List<String> messagesWithNewArticles =new ArrayList<>();
        StringBuilder postSb = new StringBuilder();

        for(PostInfo post:newPosts){
            messagesWithNewArticles.add(
                    postSb.append("✨Вышла новая статья <b>").append(post.getTitle())
                    .append("</b> в группе <b>").append(gSub.getTitle())
                    .append("</b>.✨\n\n<b>Описание:</b>").append(post.getDescription())
                    .append("\n\n<b>Ссылка:</b> ").append(getPostUrl(post.getKey())).append("\n").toString());
            postSb.setLength(0);
        }

        gSub.getUsers().stream()
                .filter(TelegramUser::isActive)
                .forEach(it -> sendMessageService.sendMessage(it.getChartId(), messagesWithNewArticles));
    }

    private void setNewLastArticleId(GroupSub gSub, List<PostInfo> newPosts) {
        newPosts.stream().mapToInt(PostInfo::getId).max()
                .ifPresent(id -> {
                    gSub.setLastArticleId(id);
                    groupSubService.save(gSub);
                });
    }

    private String getPostUrl(String key) {
        return String.format(apiGroupPath, key);
    }
}
