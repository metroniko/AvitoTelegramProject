package ru.home.avitotelegram.bot.services;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.home.avitotelegram.avitoparser.ParserAuto;
import ru.home.avitotelegram.bot.TelegramBot;
import ru.home.avitotelegram.itemInformation.fullItemInformation.CarItem;

import java.io.IOException;
import java.util.List;

@Service
public class CarParseService {

    private ParserAuto parserAuto;
    private TelegramBot telegramBot;

    public CarParseService(ParserAuto parserAuto,
                           TelegramBot telegramBot) {
        this.parserAuto = parserAuto;
        this.telegramBot = telegramBot;
    }

    public SendMessage getCarMessage(String[] car, Long chatId, boolean isFirst) throws IOException {
            List<CarItem> parse = parserAuto.parse(car, isFirst);
            //carCarcaseRepository.save(carCarcase);
            if (parse.size() == 0) {
                return new SendMessage(chatId, "Нет таких машин");
            }
            telegramBot.sendCarMessages(chatId,parse);

            CarItem carItem = parse.get(parse.size() - 1);
            return new SendMessage(chatId, String.format("%s%n -------------------%nНазвание: %s%nБренд: %d%nТип Кузова: %s%nЦвет: %s%nТип Двигателя: %d%n" +
                            "Цена: %s%nСсылка: %s%n", carItem.getBrand(), carItem.getBodyType(), carItem.getColor(), carItem.getFuelType(),
                    carItem.getPrice(), carItem.getURL()));
    }
}
