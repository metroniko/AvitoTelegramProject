package ru.home.avitotelegram.bot.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.avitotelegram.bot.botState.BotState;
import ru.home.avitotelegram.bot.cache.UserCache;
import ru.home.avitotelegram.bot.facade.TelegramFacade;
import ru.home.avitotelegram.bot.handle.handlers.InputMessageHandler;
import ru.home.avitotelegram.bot.repositories.UserRepository;
import ru.home.avitotelegram.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.home.avitotelegram.bot.botState.BotState.ASK_PRICE;

@Component
public class BotHandlerContext {

    private static  final Logger log = LoggerFactory.getLogger(BotHandlerContext.class);
    private UserCache userCache;
    private Map<BotState, InputMessageHandler>messageHandler = new HashMap<>();
    private UserRepository userRepository;

    public BotHandlerContext(List<InputMessageHandler> handlers,
                             UserCache userCache, UserRepository userRepository) {
        handlers.forEach(el -> this.messageHandler.put(el.getHandlerName(), el));
        this.userCache = userCache;
        this.userRepository = userRepository;
    }

    public InputMessageHandler getCurrentHandler(BotState botState) {
        return messageHandler.get(botState);
    }

    public InputMessageHandler findCurrentContext(Message message) {

        Long userId = message.getChatId();
        User user = userRepository.getUserByUserId(userId);
        //User user = userCache.getUserById(userId);
        log.info("findCurrentContext : user = {}", user);
        BotState botState = user.getBotState();


        if (botState == BotState.ASK_SOMETHING) return getCurrentHandler(BotState.ASK_SOMETHING);

        switch (botState) {
            case ASK_CARNAME:
            case ASK_MODEL:
            case ASK_PRICE:
                return getCurrentHandler(BotState.SUBSCRIBE_CAR);
        }

        return null;


    }


}
