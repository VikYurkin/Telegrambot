package ru.VYurkin.TelegramBot.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.VYurkin.TelegramBot.services.interfaces.FindNewPostsService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Component
public class FindNewPostJob {
    private final FindNewPostsService findNewPostService;
    @Autowired
    public FindNewPostJob(FindNewPostsService findNewPostService) {
        this.findNewPostService = findNewPostService;
    }

    @Scheduled(fixedRateString = "${bot.recountNewPostFixedRate}")
    public void findNewPosts() {
        LocalDateTime start = LocalDateTime.now();

        log.info("Find new post job started.");

        findNewPostService.findNewPosts();

        LocalDateTime end = LocalDateTime.now();

        log.info("Find new post job finished"+" "+end.toLocalTime().getMinute()+" "+". Took seconds: {}",
                end.toEpochSecond(ZoneOffset.UTC) - start.toEpochSecond(ZoneOffset.UTC));
    }
}
