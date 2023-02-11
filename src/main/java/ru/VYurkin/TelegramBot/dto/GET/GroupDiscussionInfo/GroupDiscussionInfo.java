package ru.VYurkin.TelegramBot.dto.GET.GroupDiscussionInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.VYurkin.TelegramBot.dto.GET.GroupInfo.GroupInfo;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class GroupDiscussionInfo extends GroupInfo {

    private UserDiscussionInfo userDiscussionInfo;
    private Integer commentsCount;

}
