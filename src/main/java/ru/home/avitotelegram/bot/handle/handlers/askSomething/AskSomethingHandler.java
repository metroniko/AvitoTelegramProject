package ru.home.avitotelegram.bot.handle.handlers.askSomething;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.avitotelegram.bot.botState.BotState;
import ru.home.avitotelegram.bot.handle.BotHandlerContext;
import ru.home.avitotelegram.bot.handle.handlers.InputCallbackHandler;
import ru.home.avitotelegram.bot.handle.handlers.InputMessageHandler;

@Lazy
@Component
public class AskSomethingHandler implements InputCallbackHandler {

    @Lazy
    private BotHandlerContext botHandlerContext;
    private static  final Logger log =  LoggerFactory.getLogger(AskSomethingHandler.class);
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
        return sendMessage;
    }

    @Override
    public SendMessage handleCallbackQuery(CallbackQuery callbackQuery) {


        String data = callbackQuery.getData();
        SendMessage sendMessage = null;



        if (data.equals("auto")) {
            InputCallbackHandler currentHandler = (InputCallbackHandler) botHandlerContext.getCurrentHandler(BotState.SUBSCRIBE_CAR);
            sendMessage = currentHandler.handleCallbackQuery(callbackQuery);
            log.info("handleCallbackQuery - message: {}, data: {}", sendMessage, data);
        }

        if (data.equals("somethongElse")) {

        }

        return sendMessage;



    }
}
