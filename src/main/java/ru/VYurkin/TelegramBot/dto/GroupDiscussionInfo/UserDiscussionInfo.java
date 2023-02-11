package ru.VYurkin.TelegramBot.dto.GroupDiscussionInfo;

import lombok.Data;

@Data
public class UserDiscussionInfo {
    private Boolean isBookmarked;
    private Integer lastTime;
    private Integer newCommentsCount;
}
