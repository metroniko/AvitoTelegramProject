package ru.home.avitotelegram.entity;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.home.avitotelegram.bot.botState.BotState;
import ru.home.avitotelegram.entity.typeofsubscribes.TypeOfSubscribes;
import ru.home.avitotelegram.itemInformation.fullItemInformation.AvitoItem;

import java.util.List;
import java.util.Map;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    Long userId;
    BotState botState;
    Map<TypeOfSubscribes, List<AvitoItem>> usersSubscribes;



    public User(Long userId) { this.userId = userId; }
    public long getUserId() { return userId; }
    public BotState getBotState() { return botState; }
    public void setBotState(BotState botState) { this.botState = botState; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Map<TypeOfSubscribes, List<AvitoItem>> getUsersSubscribes() {
        return usersSubscribes;
    }

    public void setUsersSubscribes(Map<TypeOfSubscribes, List<AvitoItem>> usersSubscribes) {
        this.usersSubscribes = usersSubscribes;
    }
    public void updateSubscribes(TypeOfSubscribes typeOfSubscribes, AvitoItem item) {

        List<AvitoItem> avitoItems = usersSubscribes.get(typeOfSubscribes);

        avitoItems.stream().filter(element -> element.)

    }
}
