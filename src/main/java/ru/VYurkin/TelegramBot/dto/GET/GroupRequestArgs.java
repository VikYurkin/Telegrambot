package ru.VYurkin.TelegramBot.dto.GET;

import lombok.Builder;
import lombok.Getter;
import ru.VYurkin.TelegramBot.dto.GET.GroupInfo.GroupInfoType;
import java.util.HashMap;
import java.util.Map;
import static java.util.Objects.nonNull;

@Builder
@Getter
public class GroupRequestArgs {

    private final String query;
    private final GroupInfoType type;
    private final Integer userId;
    private final String order;
    private final Boolean includeDiscussion;
    private final GroupFilter filter;

    private final Integer offset;

    private final Integer limit;

    public Map<String, Object>  populateQueries() {
        Map<String, Object>  queries = new HashMap<>();
        if(nonNull(query)) {
            queries.put("query", query);
        }
        if(nonNull(type)) {
            queries.put("type", type);
        }
        if(nonNull(type)) {
            queries.put("userId", userId);
        }
        if(nonNull(type)) {
            queries.put("order", order);
        }
        if(nonNull(filter)) {
            queries.put("filter", filter);
        }
        if(nonNull(type)) {
            queries.put("includeDiscussion", includeDiscussion);
        }
        if(nonNull(offset)) {
            queries.put("offset", offset);
        }
        if(nonNull(limit)) {
            queries.put("limit", limit);
        }
        return queries;
    }

}
