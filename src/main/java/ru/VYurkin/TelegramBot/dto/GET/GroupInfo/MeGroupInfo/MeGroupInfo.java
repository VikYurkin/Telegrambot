package ru.VYurkin.TelegramBot.dto.GET.GroupInfo.MeGroupInfo;

import lombok.Data;

@Data
public class MeGroupInfo {
    private MeGroupInfoStatus status;
    private Integer userGroupId;
}
