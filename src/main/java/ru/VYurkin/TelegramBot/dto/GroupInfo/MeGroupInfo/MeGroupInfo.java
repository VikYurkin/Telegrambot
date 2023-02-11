package ru.VYurkin.TelegramBot.dto.GroupInfo.MeGroupInfo;

import lombok.Data;

@Data
public class MeGroupInfo {
    private MeGroupInfoStatus status;
    private Integer userGroupId;
}
