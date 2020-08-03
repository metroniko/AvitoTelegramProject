package ru.home.avitotelegram.entity;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.home.avitotelegram.bot.botState.BotState;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    Long userId;
    BotState botState;

    public User(Long userId) { this.userId = userId; }
    public long getUserId() { return userId; }
    public BotState getBotState() { return botState; }
    public void setBotState(BotState botState) { this.botState = botState; }
    public void setUserId(Long userId) { this.userId = userId; }
}
