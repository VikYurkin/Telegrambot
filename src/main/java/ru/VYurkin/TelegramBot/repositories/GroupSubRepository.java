package ru.VYurkin.TelegramBot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.VYurkin.TelegramBot.models.GroupSub;

public interface GroupSubRepository extends JpaRepository<GroupSub, Integer> {
}
