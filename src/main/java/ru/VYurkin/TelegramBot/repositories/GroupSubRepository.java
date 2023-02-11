package ru.VYurkin.TelegramBot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.VYurkin.TelegramBot.models.GroupSub;
@Repository
public interface GroupSubRepository extends JpaRepository<GroupSub, Integer> {
}
