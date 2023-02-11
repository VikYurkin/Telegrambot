package ru.VYurkin.TelegramBot.services;

import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;
import ru.VYurkin.TelegramBot.dto.GroupDiscussionInfo.GroupDiscussionInfo;
import ru.VYurkin.TelegramBot.models.GroupSub;
import ru.VYurkin.TelegramBot.models.TelegramUser;
import ru.VYurkin.TelegramBot.repositories.GroupSubRepository;
import java.util.Optional;

@Service
public class GroupSubServiceImpl implements GroupSubService {
    private final GroupSubRepository groupSubRepository;
    private  final TelegramUserService telegramUserService;

    public GroupSubServiceImpl(GroupSubRepository groupSubRepository, TelegramUserService telegramUserService) {
        this.groupSubRepository = groupSubRepository;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo) {
        TelegramUser telegramUser = telegramUserService.findByChatId(chatId).orElseThrow(NotFoundException::new);
        GroupSub groupSub;
        Optional<GroupSub> groupSubFromDB = groupSubRepository.findById(groupDiscussionInfo.getId());
        if(groupSubFromDB.isPresent()) {
        groupSub = groupSubFromDB.get();
        Optional<TelegramUser> first = groupSub.getUsers().stream()
                .filter(x-> x.getChartId().equalsIgnoreCase(chatId)).findFirst();
            if(first.isEmpty()){groupSub.addUser(telegramUser);}
        }else{
            groupSub = new GroupSub();
            groupSub.addUser(telegramUser);
            groupSub.setId(groupDiscussionInfo.getId());
            groupSub.setTitle(groupDiscussionInfo.getTitle());
        }
        return groupSubRepository.save(groupSub);
    }
}
