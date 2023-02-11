package ru.VYurkin.TelegramBot.dto.POST.PostInfo;

import lombok.Data;
import ru.VYurkin.TelegramBot.dto.GET.GroupDiscussionInfo.UserDiscussionInfo;
import ru.VYurkin.TelegramBot.dto.GET.GroupInfo.GroupInfo;
import ru.VYurkin.TelegramBot.dto.POST.BaseUserInfo.BaseUserInfo;
import ru.VYurkin.TelegramBot.dto.POST.PostInfo.LikesInfo.LikesInfo;

@Data
public class PostInfo {
    private BaseUserInfo authorInfo;
    private Integer commentsCount;
    private String content;
    private Long createdTime;
    private String description;
    private GroupInfo groupInfo;
    private Integer id;
    private String key;
    private Language language;
    private LikesInfo likesInfo;
    private GroupInfo originalGroupInfo;
    private String pictureUrl;
    private Double rating;
    private Integer ratingCount;
    private String title;
    private PostType type;
    private Long updatedTime;
    private UserDiscussionInfo userDiscussionInfo;
    private Integer views;
    private VisibilityStatus visibilityStatus;
}
