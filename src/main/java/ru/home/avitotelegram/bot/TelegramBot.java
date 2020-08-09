package ru.home.avitotelegram.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.home.avitotelegram.bot.facade.TelegramFacade;
import ru.home.avitotelegram.itemInformation.fullItemInformation.CarItem;

import java.util.List;


@Component
public class TelegramBot extends TelegramWebhookBot {

    private static  final Logger log =  LoggerFactory.getLogger(TelegramWebhookBot.class);

    private TelegramFacade telegramFacade;

    public TelegramBot(DefaultBotOptions options, TelegramFacade telegramFacade) {
        super(options);
        this.telegramFacade = telegramFacade;
    }
    public void sendCarMessages(Long chatId, List<CarItem> carItems) {
        carItems.forEach(el -> {
            try {
                execute(new SendMessage(chatId, el.toString()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {

        return telegramFacade.handleMessage(update);
    }

    @Override
    public String getBotUsername() {
        return "ChoseSomeVpnBot";
    }

    @Override
    public String getBotToken() {
        return "1247886306:AAEXXm8uwbIVvaMQSctfQ7fXS6sUqb2eW2c";
    }

    @Override
    public String getBotPath() {
        return "https://e532deb9cb43.ngrok.io";
    }
}
