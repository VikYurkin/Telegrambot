package ru.VYurkin.TelegramBot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.VYurkin.TelegramBot.client.PostClient;
import ru.VYurkin.TelegramBot.client.PostClientImpl;
import ru.VYurkin.TelegramBot.dto.POST.PostInfo.PostInfo;

import java.util.List;

@DisplayName("Integration-level testing for PostClient")
public class PostClientTest {
    public static final String apiPostPath = "https://javarush.ru/api/1.0/rest";
    private final PostClient postClient = new PostClientImpl(apiPostPath);

    @Test
    public void shouldProperlyGetNew15Posts() {
        //when
        List<PostInfo> newPosts = postClient.findNewPosts(30, 3218);

        //then
        Assertions.assertEquals(15, newPosts.size());
    }
}
