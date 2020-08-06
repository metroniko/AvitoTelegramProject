package ru.home.avitotelegram.bot.handle.handlers.askSomething;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.avitotelegram.bot.botState.BotState;
import ru.home.avitotelegram.bot.handle.BotHandlerContext;
import ru.home.avitotelegram.bot.handle.handlers.InputMessageHandler;

@Lazy
@Component
public class AskSomethingHandler implements InputMessageHandler {

    @Lazy
    private BotHandlerContext botHandlerContext;

    @Lazy
    public AskSomethingHandler(BotHandlerContext botHandlerContext) {
        this.botHandlerContext = botHandlerContext;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.ASK_SOMETHING;
    }

    @Override
    public SendMessage handle(Message message) {

        String messageText = message.getText();
        
        SendMessage sendMessage = null;

        if (messageText.equals("auto")) {
            InputMessageHandler currentHandler = botHandlerContext.getCurrentHandler(BotState.SUBSCRIBE_CAR);
            sendMessage = currentHandler.handle(message);
        }

        /**
         *
         * ОБРАБОТЧИК ЕЩЁ ЧЕГО НИБУДЬ
         * ОБРАБОТЧИК ЕЩЁ ЧЕГО НИБУДЬ
         * ОБРАБОТЧИК ЕЩЁ ЧЕГО НИБУДЬ
         * ОБРАБОТЧИК ЕЩЁ ЧЕГО НИБУДЬ
         * ОБРАБОТЧИК ЕЩЁ ЧЕГО НИБУДЬ
         * */
        if (messageText.equals("somethongElse")) {

        }
        return sendMessage;
    }
}
