package ru.home.avitotelegram.bot.handle.handlers.hello;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.avitotelegram.bot.botState.BotState;
import ru.home.avitotelegram.bot.cache.UserCache;
import ru.home.avitotelegram.bot.handle.handlers.InputMessageHandler;
import ru.home.avitotelegram.entity.User;

@Component
public class HelloHandler implements InputMessageHandler {

    private UserCache userCache;

    public HelloHandler(UserCache userCache) {
        this.userCache = userCache;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.HELLO;
    }

    @Override
    public SendMessage handle(Message message) {
        long chatId = message.getChatId();
        User user = userCache.getUserById(chatId);
        userCache.updateUserBotState(user, BotState.ASK_SOMETHING);
        return new SendMessage(chatId, "Давай подписываться");
    }
}
