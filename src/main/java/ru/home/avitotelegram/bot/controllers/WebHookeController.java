package ru.home.avitotelegram.bot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.avitotelegram.bot.TelegramBot;

@Controller
public class WebHookeController {

    private TelegramBot telegramBot;

    public WebHookeController(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @PostMapping("/")
    public BotApiMethod<?> getWebHook(@RequestBody Update updateBody) {
        return telegramBot.onWebhookUpdateReceived(updateBody);
    }
}
