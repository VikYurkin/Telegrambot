package ru.VYurkin.TelegramBot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import ru.VYurkin.TelegramBot.models.TelegramUser;
import ru.VYurkin.TelegramBot.repositories.TelegramUserRepository;

import java.util.List;
import java.util.Optional;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TelegramUserRepositoryIT {
    @Autowired
    private TelegramUserRepository telegramUserRepository;

    @Sql(scripts={"/sql/clearDbs.sql", "/sql/telegram_users.sql"})
    @Test
    public void shouldProperlyFindAllActiveUsers(){
        List<TelegramUser> users = telegramUserRepository.findAllByActiveTrue();

        Assertions.assertEquals(5,users.size());

    }

    @Sql(scripts={"/sql/clearDbs.sql"})
    @Test
    public void shouldProperlySaveTelegramUse(){
        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setChartId("1234567890");
        telegramUser.setActive(false);
        telegramUserRepository.save(telegramUser);

        Optional <TelegramUser> saved = telegramUserRepository.findById(telegramUser.getChartId());

        Assertions.assertTrue(saved.isPresent());
        Assertions.assertEquals(telegramUser, saved.get());
    }

}
