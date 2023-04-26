package ru.VYurkin.TelegramBot.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@EqualsAndHashCode(exclude = "groupSubs")
@Table(name="tg_user")
public class TelegramUser {

    @Id
    @Column(name = "chat_id")
    private Long chartId;

    @Column(name = "active")
    private boolean active;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<GroupSub> groupSubs;

}
