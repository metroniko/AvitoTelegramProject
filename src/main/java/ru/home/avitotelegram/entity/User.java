package ru.home.avitotelegram.entity;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.home.avitotelegram.bot.botState.BotState;
import ru.home.avitotelegram.entity.typeofsubscribes.TypeOfSubscribes;
import ru.home.avitotelegram.itemInformation.fullItemInformation.AvitoItem;
import ru.home.avitotelegram.itemInformation.fullItemInformation.CarItem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Users")
public class User {
    /**
     * ПЕРЕОПРЕДЕЛИТЬ TOSTRING
     * */

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq", allocationSize = 1)
    @Id
    @Column(name = "user_id")
    Long userId;


    @Column(name = "bot_state")
    BotState botState;
//    Map<TypeOfSubscribes, List<AvitoItem>> usersSubscribes;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<CarItem> carItems = new ArrayList<>();



    public User(Long userId) { this.userId = userId; }
    public long getUserId() { return userId; }
    public BotState getBotState() { return botState; }
    public void setBotState(BotState botState) { this.botState = botState; }
    public void setUserId(Long userId) { this.userId = userId; }

    public User() {
    }

//    public Map<TypeOfSubscribes, List<AvitoItem>> getUsersSubscribes() {
//        return usersSubscribes;
//    }

//    public void setUsersSubscribes(Map<TypeOfSubscribes, List<AvitoItem>> usersSubscribes) {
//        this.usersSubscribes = usersSubscribes;
//    }
//    public void updateSubscribes(TypeOfSubscribes typeOfSubscribes, AvitoItem item) {
//
//        List<AvitoItem> avitoItems = usersSubscribes.get(typeOfSubscribes);
//
//        //avitoItems.stream().filter(element -> element.)
//
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                botState == user.botState &&
                Objects.equals(usersSubscribes, user.usersSubscribes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, botState, usersSubscribes);
    }
}
