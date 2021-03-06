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
        carItems.forEach(carItem -> {
            try {
                execute(new SendMessage(chatId, String.format("%s%n -------------------%nНазвание:   %s%nЦвет: %s%nТип Двигателя: %s%n" +
                                "Цена: %s%nСсылка: %s%n", carItem.getBrand(), carItem.getBodyType(), carItem.getColor(), carItem.getFuelType(),
                        carItem.getPrice(), carItem.getURL())));
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
        return "https://7398a12fae5e.ngrok.io";
    }
}
