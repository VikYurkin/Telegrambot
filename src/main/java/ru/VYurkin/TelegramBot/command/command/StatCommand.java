package ru.VYurkin.TelegramBot.command.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.VYurkin.TelegramBot.command.Command;
import ru.VYurkin.TelegramBot.command.annotation.AdminCommand;
import ru.VYurkin.TelegramBot.dto.statistic.StatisticDTO;
import ru.VYurkin.TelegramBot.services.interfaces.SendBotMessageService;
import ru.VYurkin.TelegramBot.services.interfaces.StatisticsService;

@AdminCommand
@Component
public class StatCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    private final StatisticsService statisticsService;

    public final static String STAT_MESSAGE = """
            ✨<b>Подготовил статистику</b>✨
            - Количество активных пользователей: %s
            - Количество неактивных пользователей: %s
            - Среднее количество групп на одного пользователя: %s

            <b>Информация по активным группам</b>:
            %s""";

    public StatCommand(SendBotMessageService sendBotMessageService, StatisticsService statisticsService) {
        this.sendBotMessageService = sendBotMessageService;
        this.statisticsService = statisticsService;
    }

    @Override
    public void execute(Update update) {
        StatisticDTO statisticDTO = statisticsService.countBotStatistic();

        StringBuilder collectedGroups = new StringBuilder();

        statisticDTO.getGroupStatDTOs().forEach(it->collectedGroups.append(it.getTitle()).append(" (id = ")
                .append(it.getId())
                .append(") - ")
                .append(it.getActiveUserCount())
                .append(" подписчиков"));

        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), String.format(STAT_MESSAGE,
                statisticDTO.getActiveUserCount(),
                statisticDTO.getInactiveUserCount(),
                statisticDTO.getAverageGroupCountByUser(),
                collectedGroups));
    }
}
