package ru.home.avitotelegram.entity;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.home.avitotelegram.bot.botState.BotState;
import ru.home.avitotelegram.itemInformation.fullItemInformation.CarItem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Users")
public class User {
    /**
     * ПЕРЕОПРЕДЕЛИТЬ TOSTRING
     * */
    @Id
    @Column(name = "user_id")
    Long userId;


    @Column(name = "bot_state")
    BotState botState;
//    Map<TypeOfSubscribes, List<AvitoItem>> usersSubscribes;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    Collection<CarCarcase> carItems = new ArrayList<>();



    public User(Long userId) { this.userId = userId; }
    public Long getUserId() { return userId; }

    public Collection<CarCarcase> getCarItems() {
        return carItems;
    }

    public void setCarItems(Collection<CarCarcase> carItems) {
        this.carItems = carItems;
    }

    @Enumerated(EnumType.ORDINAL)
    public BotState getBotState() { return botState; }
    public void setBotState(BotState botState) { this.botState = botState; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void addcarItems(CarCarcase carCarcase) {
        carItems.add(carCarcase);
    }
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
        return botState == user.botState &&
                carItems.equals(user.carItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(botState, carItems);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", botState=" + botState +
                ", carItems=" + carItems +
                '}';
    }
}
