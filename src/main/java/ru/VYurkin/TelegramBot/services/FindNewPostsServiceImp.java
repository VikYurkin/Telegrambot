package ru.VYurkin.TelegramBot.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.VYurkin.TelegramBot.clients.interfaces.PostClient;
import ru.VYurkin.TelegramBot.dto.POST.PostInfo.PostInfo;
import ru.VYurkin.TelegramBot.models.GroupSub;
import ru.VYurkin.TelegramBot.models.TelegramUser;
import ru.VYurkin.TelegramBot.services.interfaces.FindNewPostsService;
import ru.VYurkin.TelegramBot.services.interfaces.GroupSubService;
import ru.VYurkin.TelegramBot.services.interfaces.SendBotMessageService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FindNewPostsServiceImp implements FindNewPostsService {
    private final String apiGroupPath;
    private final GroupSubService groupSubService;
    private final PostClient postClient;
    private final SendBotMessageService sendMessageService;

    public FindNewPostsServiceImp(@Value("${api.path}")String apiGroupPath,
                                  GroupSubService groupSubService,
                                  PostClient postClient,
                                  SendBotMessageService sendMessageService) {
        this.apiGroupPath = apiGroupPath+ "/posts%s";
        this.groupSubService = groupSubService;
        this.postClient = postClient;
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void findNewPosts() {
        groupSubService.findAll().forEach(gSub->{
            List<PostInfo> newPosts = postClient.findNewPosts(gSub.getId(), gSub.getLastPostId());
            setNewLastPostId(gSub, newPosts);
            notifySubscribersAboutNewPosts(gSub, newPosts);
        });
    }

    private void notifySubscribersAboutNewPosts(GroupSub gSub, List<PostInfo> newPosts) {
        Collections.reverse(newPosts);
        List<String> messagesWithNewPosts =new ArrayList<>();
        StringBuilder postSb = new StringBuilder();

        for(PostInfo post:newPosts){
            messagesWithNewPosts.add(
                    postSb.append("✨Вышла новая статья <b>").append(post.getTitle())
                    .append("</b> в группе <b>").append(gSub.getTitle())
                    .append("</b>.✨\n\n<b>Описание:</b>").append(post.getDescription())
                    .append("\n\n<b>Ссылка:</b> ").append(getPostUrl(post.getKey())).append("\n").toString());
            postSb.setLength(0);
        }

        gSub.getUsers().stream()
                .filter(TelegramUser::isActive)
                .forEach(it -> sendMessageService.sendMessage(it.getChartId(), messagesWithNewPosts));
    }

    private void setNewLastPostId(GroupSub gSub, List<PostInfo> newPosts) {
        newPosts.stream().mapToInt(PostInfo::getId).max()
                .ifPresent(id -> {
                    gSub.setLastPostId(id);
                    groupSubService.save(gSub);
                });
    }

    private String getPostUrl(String key) {
        return String.format(apiGroupPath, key);
    }
}
