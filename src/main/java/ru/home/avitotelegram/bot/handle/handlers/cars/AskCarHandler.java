package ru.home.avitotelegram.bot.handle.handlers.cars;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.avitotelegram.bot.botState.BotState;
import ru.home.avitotelegram.bot.cache.UserCache;
import ru.home.avitotelegram.bot.handle.handlers.InputMessageHandler;
import ru.home.avitotelegram.entity.User;
import ru.home.avitotelegram.itemInformation.DTO.CarDTO;
import ru.home.avitotelegram.itemInformation.DTOCollector;

import java.util.HashMap;

@Component
public class AskCarHandler implements InputMessageHandler {

    private BotState botState = BotState.SUBSCRIBE_CAR;
    private UserCache userCache;
    private DTOCollector dtoCollector;


    public AskCarHandler(UserCache userCache, DTOCollector dtoCollector) {
        this.userCache = userCache;
        this.dtoCollector = dtoCollector;
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

        SendMessage sendMessage = null;

        /**
         * ПОДРОБНЕЕ ПОСМОТРЕТЬ
         * ПОДРОБНЕЕ ПОСМОТРЕТЬ
         * ПОДРОБНЕЕ ПОСМОТРЕТЬ
         * ПОДРОБНЕЕ ПОСМОТРЕТЬ
         * ПОДРОБНЕЕ ПОСМОТРЕТЬ*/

        if (messageText.equals("auto")) {
            CarDTO carDTO = new CarDTO();
            dtoCollector.setCarDTOElement(carDTO);
            dtoCollector.setUserCar(chatId, carDTO.getCarDTOId());
            user.setUsersSubscribes(new HashMap<>());
            userCache.updateUserBotState(user, BotState.ASK_CARNAME);
            return new SendMessage(message.getChatId(), "Марку?");
        }
        if (user.getBotState().equals(BotState.ASK_CARNAME)) {

            CarDTO carDTOElement = dtoCollector.getCarDTOElement(chatId);
            dtoCollector.removeCarDTOElement(carDTOElement);
            carDTOElement.setCarName(messageText);
            dtoCollector.setCarDTOElement(carDTOElement);
            userCache.updateUserBotState(user, BotState.ASK_MODEL);
        }
        if (user.getBotState().equals(BotState.ASK_MODEL)) {
            /**
             *Дописать обработчик
             *Дописать обработчик
             *Дописать обработчик
             *Дописать обработчик
             *Дописать обработчик
             * */
        }

        return sendMessage;
    }
}
