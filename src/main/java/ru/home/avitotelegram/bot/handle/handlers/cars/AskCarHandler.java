package ru.home.avitotelegram.bot.handle.handlers.cars;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.avitotelegram.bot.botState.BotState;
import ru.home.avitotelegram.bot.cache.UserCache;
import ru.home.avitotelegram.bot.handle.handlers.InputMessageHandler;
import ru.home.avitotelegram.entity.User;

@Component
public class AskCarHandler implements InputMessageHandler {

    private BotState botState = BotState.ASK_CARNAME;
    private UserCache userCache;


    public AskCarHandler(UserCache userCache) {
        this.userCache = userCache;
    }


    @Override
    public BotState getHandlerName() {
        return botState;
    }

    @Override
    public SendMessage handle(Message message) {

        String messageText = message.getText();
        Long chatId = message.getChatId();
        User user = userCache.getUserById(chatId);
        user.setBotState(BotState.ASK_CARMARK);
        return new SendMessage(message.getChatId(), "Марку?");
    }
}
