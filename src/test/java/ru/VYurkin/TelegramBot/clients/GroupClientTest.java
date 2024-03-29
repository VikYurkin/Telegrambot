package ru.VYurkin.TelegramBot.clients;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.VYurkin.TelegramBot.clients.interfaces.GroupClient;
import ru.VYurkin.TelegramBot.dto.GET.GroupDiscussionInfo.GroupDiscussionInfo;
import ru.VYurkin.TelegramBot.dto.GET.GroupInfo.GroupInfo;
import ru.VYurkin.TelegramBot.dto.GET.GroupRequestArgs;
import java.util.List;
import static ru.VYurkin.TelegramBot.dto.GET.GroupInfo.GroupInfoType.TECH;

@DisplayName("Integration-level testing for GroupClientImplTest")
public class GroupClientTest {


    private final GroupClient groupClient = new GroupClientImpl("https://javarush.com/api/1.0/rest");

    @Test
    public void shouldProperlyGetGroupsWithEmptyArgs() {
        //given
        GroupRequestArgs args = GroupRequestArgs.builder().build();


        //when
        List<GroupInfo> groupList = groupClient.getGroupList(args);


        //then
        Assertions.assertNotNull(groupList);
        Assertions.assertFalse(groupList.isEmpty());
    }

    @Test
    public void shouldProperlyGetWithOffSetAndLimit() {
        //given
        GroupRequestArgs args = GroupRequestArgs.builder()
                .offset(1)
                .limit(3)
                .build();

        //when
        List<GroupInfo> groupList = groupClient.getGroupList(args);

        //then
        Assertions.assertNotNull(groupList);
        Assertions.assertEquals(3, groupList.size());
    }

    @Test
    public void shouldProperlyGetGroupsDiscWithEmptyArgs() {
        //given
        GroupRequestArgs args = GroupRequestArgs.builder().build();

        //when
        List<GroupDiscussionInfo> groupList = groupClient.getGroupDiscussionList(args);

        //then
        Assertions.assertNotNull(groupList);
        Assertions.assertFalse(groupList.isEmpty());
    }

    @Test
    public void shouldProperlyGetGroupDiscWithOffSetAndLimit() {
        //given
        GroupRequestArgs args = GroupRequestArgs.builder()
                .offset(1)
                .limit(3)
                .build();

        //when
        List<GroupDiscussionInfo> groupList = groupClient.getGroupDiscussionList(args);

        //then
        Assertions.assertNotNull(groupList);
        Assertions.assertEquals(3, groupList.size());
    }

    @Test
    public void shouldProperlyGetGroupCount() {
        //given
        GroupRequestArgs args = GroupRequestArgs.builder().build();

        //when
        Integer groupCount = groupClient.getGroupCount(args);

        //then
        Assertions.assertEquals(33, groupCount);
    }

    @Test
    public void shouldProperlyGetGroupTECHCount() {
        //given
        GroupRequestArgs args = GroupRequestArgs.builder()
                .type(TECH)
                .build();

        //when
        Integer groupCount = groupClient.getGroupCount(args);

        //then
        Assertions.assertEquals(7, groupCount);
    }

    @Test
    public void shouldProperlyGetGroupById() {
        //given
        Integer androidGroupId = 16;

        //when
        GroupDiscussionInfo groupById = groupClient.getGroupById(androidGroupId);

        //then
        Assertions.assertNotNull(groupById);
        Assertions.assertEquals(16, groupById.getId());
        Assertions.assertEquals(TECH, groupById.getType());
        Assertions.assertEquals("android", groupById.getKey());
    }

}
