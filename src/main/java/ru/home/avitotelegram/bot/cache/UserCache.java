package ru.home.avitotelegram.bot.cache;

import org.springframework.stereotype.Component;
import ru.home.avitotelegram.bot.botState.BotState;
import ru.home.avitotelegram.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserCache {

    private List<User> users = new ArrayList<>();

    public UserCache() {
    }

    public User getUserById(Long chatId) {

        List<User> users = this.users.stream()
                .filter(user -> user.getUserId() == chatId)
                .collect(Collectors.toList());
        User user = null;
        if (users.size() == 1)  {
            user = users.get(0);

        }
        return user;
    }

    public void addUser(User user) {
        List<User> collect = this.users.stream()
                .filter(user1 -> user1.getUserId() == user.getUserId())
                .collect(Collectors.toList());
        if (collect.size() > 0) {
            return;
        }
        users.add(user);
    }
    public void updateUserBotState(User user, BotState botState) {
        User userById = getUserById(user.getUserId());
        users.remove(userById);
        userById.setBotState(botState);
        users.add(user);

    }

}
