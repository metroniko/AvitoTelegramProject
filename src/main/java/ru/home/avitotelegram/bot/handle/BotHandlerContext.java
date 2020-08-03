package ru.home.avitotelegram.bot.handle;

import org.springframework.stereotype.Component;
import ru.home.avitotelegram.bot.botState.BotState;
import ru.home.avitotelegram.bot.handle.handlers.InputMessageHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class BotHandlerContext {

    Map<BotState, InputMessageHandler>messageHandler = new HashMap<>();

    public BotHandlerContext(ArrayList<InputMessageHandler> handlers) {
        handlers.forEach(el -> messageHandler.put(el.getHandlerName(), el));
    }

    public InputMessageHandler getCurrentHandler(BotState botState) {
        return messageHandler.get(botState);
    }


}
