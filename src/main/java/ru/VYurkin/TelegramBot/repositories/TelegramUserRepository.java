package ru.VYurkin.TelegramBot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.VYurkin.TelegramBot.models.TelegramUser;
import java.util.List;

@Repository
public interface TelegramUserRepository extends JpaRepository<TelegramUser, Long> {
    List<TelegramUser> findAllByActiveTrue();
    List<TelegramUser> findAllByActiveFalse();
}
