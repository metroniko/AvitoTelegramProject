package ru.home.avitotelegram.bot.handle.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.avitotelegram.bot.botState.BotState;

public class AskSomethingHandler implements InputMessageHandler {

    @Override
    public BotState getHandlerName() {
        return BotState.ASK_SOMETHING;
    }

    @Override
    public SendMessage handle(Message message) {

        if (message.getText().equals("auto")) {

        }
        return null;
    }
}
