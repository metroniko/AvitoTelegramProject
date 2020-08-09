package ru.home.avitotelegram.bot.handle.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface InputCallbackHandler extends InputMessageHandler {
    SendMessage handleCallbackQuery(CallbackQuery callbackQuery);

}
