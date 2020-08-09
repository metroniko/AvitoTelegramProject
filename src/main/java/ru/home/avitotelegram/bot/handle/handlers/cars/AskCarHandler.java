package ru.home.avitotelegram.bot.handle.handlers.cars;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.avitotelegram.avitoparser.ParserAuto;
import ru.home.avitotelegram.bot.TelegramBot;
import ru.home.avitotelegram.bot.botState.BotState;
import ru.home.avitotelegram.bot.cache.UserCache;
import ru.home.avitotelegram.bot.handle.handlers.InputCallbackHandler;
import ru.home.avitotelegram.entity.User;
import ru.home.avitotelegram.itemInformation.DTO.CarDTO;
import ru.home.avitotelegram.itemInformation.DTOCollector;
import ru.home.avitotelegram.itemInformation.fullItemInformation.CarItem;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Lazy
@Component
public class AskCarHandler implements InputCallbackHandler {

    private BotState botState = BotState.SUBSCRIBE_CAR;
    private UserCache userCache;
    private DTOCollector dtoCollector;
    private ParserAuto parserAuto;
    private TelegramBot telegramBot;

    @Lazy
    public AskCarHandler(UserCache userCache,
                         DTOCollector dtoCollector,
                         ParserAuto parserAuto,
                         TelegramBot telegramBot) {
        this.userCache = userCache;
        this.dtoCollector = dtoCollector;
        this.parserAuto = parserAuto;
        this.telegramBot = telegramBot;
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
        return handleInputMessage(messageText, chatId, user);
    }

    @Override
    public SendMessage handleCallbackQuery(CallbackQuery callbackQuery) {

        String data = callbackQuery.getData();
        Long chatId = callbackQuery.getMessage().getChatId();
        User user = userCache.getUserById(chatId);
        return handleInputMessage(data, chatId, user);
    }


    private SendMessage handleInputMessage(String messageText, Long chatId, User user) {
        SendMessage sendMessage = null;

        if (messageText.equals("auto")) {
            CarDTO carDTO = new CarDTO();
            dtoCollector.setCarDTOElement(carDTO);
            dtoCollector.setUserCar(chatId, carDTO.getCarDTOId());
            user.setUsersSubscribes(new HashMap<>());
            userCache.updateUserBotState(user, BotState.ASK_CARNAME);
            return new SendMessage(chatId, "Марку?");
        }
        if (user.getBotState().equals(BotState.ASK_CARNAME)) {

            CarDTO carDTOElement = dtoCollector.getCarDTOElement(chatId);
            dtoCollector.removeCarDTOElement(carDTOElement);
            carDTOElement.setCarName(messageText);
            dtoCollector.setCarDTOElement(carDTOElement);
            userCache.updateUserBotState(user, BotState.ASK_MODEL);
            return new SendMessage(chatId, "Модель?");
        }
        if (user.getBotState().equals(BotState.ASK_MODEL)) {
            CarDTO carDTOElement = dtoCollector.getCarDTOElement(chatId);
            dtoCollector.removeCarDTOElement(carDTOElement);
            carDTOElement.setCarMark(messageText);
            dtoCollector.setCarDTOElement(carDTOElement);
            userCache.updateUserBotState(user, BotState.ASK_PRICE);
            return new SendMessage(chatId, "Цена?");

        }
        if(user.getBotState().equals(BotState.ASK_PRICE)) {
            CarDTO carDTOElement = dtoCollector.getCarDTOElement(chatId);
            dtoCollector.removeCarDTOElement(carDTOElement);
            carDTOElement.setCarPrice(messageText);
            dtoCollector.setCarDTOElement(carDTOElement);
            userCache.updateUserBotState(user, BotState.ASK_SOMETHING);
            String[] car = new String[3];
            car[0] = carDTOElement.getCarName();
            car[1] = carDTOElement.getCarMark();
            car[2] = carDTOElement.getCarPrice();
            try {
                List<CarItem> parse = parserAuto.parse(car);
                telegramBot.sendCarMessages(chatId ,parse);
                return new SendMessage(chatId, parse.get(parse.size() - 1).toString());
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        return sendMessage;
    }
}
