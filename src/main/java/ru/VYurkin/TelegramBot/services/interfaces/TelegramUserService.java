package ru.VYurkin.TelegramBot.services.interfaces;

import ru.VYurkin.TelegramBot.models.TelegramUser;

import java.util.List;
import java.util.Optional;

public interface TelegramUserService {

    void save(TelegramUser telegramUser);

    List<TelegramUser> retrieveAllActiveUsers();

    Optional<TelegramUser> findByChatId(Long chatId);

    List<TelegramUser> findAllInActiveUsers();
    List<TelegramUser> findAllActiveUsers();
}
