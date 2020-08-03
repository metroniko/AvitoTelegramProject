package ru.home.avitotelegram.bot.handle.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.home.avitotelegram.bot.botState.BotState;
import org.telegram.telegrambots.meta.api.objects.Message;


public interface InputMessageHandler {

    BotState getHandlerName();

    SendMessage handle(Message message);

}
