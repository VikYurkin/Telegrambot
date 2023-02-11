package ru.VYurkin.TelegramBot.client;

import ru.VYurkin.TelegramBot.dto.POST.PostInfo.PostInfo;
import java.util.List;

public interface PostClient {
    List<PostInfo> findNewPosts(Integer groupId, Integer lastPostId);
}
