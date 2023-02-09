package ru.VYurkin.TelegramBot.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="tg_user")
public class TelegramUser {

    @Id
    @Column(name = "chat_id")
    private String chartId;

    @Column(name = "active")
    private boolean active;
}
