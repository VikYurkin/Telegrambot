package ru.VYurkin.TelegramBot.dto.GroupInfo;

import lombok.Data;
import lombok.ToString;
import ru.VYurkin.TelegramBot.dto.GroupInfo.MeGroupInfo.MeGroupInfo;

@Data
@ToString
public class GroupInfo {

    private Integer id;
    private String avatarUrl;
    private String createTime;
    private String description;
    private String key;
    private Integer levelToEditor;
    private MeGroupInfo meGroupInfo;
    private String pictureUrl;
    private String title;
    private GroupInfoType type;
    private Integer userCount;
    private GroupVisibilityStatus visibilityStatus;
}
