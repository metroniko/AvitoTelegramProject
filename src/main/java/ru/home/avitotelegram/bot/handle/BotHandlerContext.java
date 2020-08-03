package ru.home.avitotelegram.bot.handle;

import jdk.internal.util.xml.impl.Input;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.avitotelegram.bot.botState.BotState;
import ru.home.avitotelegram.bot.cache.UserCache;
import ru.home.avitotelegram.bot.handle.handlers.InputMessageHandler;
import ru.home.avitotelegram.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class BotHandlerContext {

    UserCache userCache;

    Map<BotState, InputMessageHandler>messageHandler = new HashMap<>();

    public BotHandlerContext(ArrayList<InputMessageHandler> handlers, UserCache userCache) {
        handlers.forEach(el -> messageHandler.put(el.getHandlerName(), el));
        this.userCache = userCache;
    }

    public InputMessageHandler getCurrentHandler(BotState botState) {
        return messageHandler.get(botState);
    }

    public InputMessageHandler findCurrentContext(Message message) {

        Long userId = message.getChatId();
        User user = userCache.getUserById(userId);
        BotState botState = user.getBotState();

        switch (botState) {
            case ASK_SOMETHING:
            case ASK_CARNAME:
                return getCurrentHandler(BotState.ASK_SOMETHING);
        }

        return null;


    }


}
