package ru.home.avitotelegram.bot.handle.handlers.cars;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.avitotelegram.avitoparser.ParserAuto;
import ru.home.avitotelegram.bot.TelegramBot;
import ru.home.avitotelegram.bot.botState.BotState;
import ru.home.avitotelegram.bot.handle.handlers.InputCallbackHandler;
import ru.home.avitotelegram.bot.repositories.UserRepository;
import ru.home.avitotelegram.bot.services.CarParseService;
import ru.home.avitotelegram.entity.CarCarcase;
import ru.home.avitotelegram.entity.User;
import ru.home.avitotelegram.itemInformation.DTO.CarDTO;
import ru.home.avitotelegram.itemInformation.DTOCollector;
import ru.home.avitotelegram.itemInformation.fullItemInformation.CarItem;

import java.io.IOException;
import java.util.List;

@Lazy
@Component
public class AskCarHandler implements InputCallbackHandler {

    private BotState botState = BotState.SUBSCRIBE_CAR;
    private DTOCollector dtoCollector;
    private ParserAuto parserAuto;
    private TelegramBot telegramBot;
    private UserRepository userRepository;
    private CarParseService carParseService;


    @Lazy
    public AskCarHandler(DTOCollector dtoCollector,
                         ParserAuto parserAuto,
                         TelegramBot telegramBot,
                         UserRepository userRepository,
                         CarParseService carParseService) {
        this.dtoCollector = dtoCollector;
        this.parserAuto = parserAuto;
        this.telegramBot = telegramBot;
        this.userRepository = userRepository;
        this.carParseService = carParseService;

    }


    @Override
    public BotState getHandlerName() {
        return botState;
    }

    @Override
    public SendMessage handle(Message message) {
        String messageText = message.getText();
        Long chatId = message.getChatId();

        User user = userRepository.getUserByUserId(chatId);
        //User user = userCache.getUserById(chatId);
        return handleInputMessage(messageText, chatId, user);
    }

    @Override
    public SendMessage handleCallbackQuery(CallbackQuery callbackQuery) {

        String data = callbackQuery.getData();
        Long chatId = callbackQuery.getMessage().getChatId();
        //User user = userCache.getUserById(chatId);
        User user = userRepository.getUserByUserId(chatId);
        return handleInputMessage(data, chatId, user);
    }


    private SendMessage handleInputMessage(String messageText, Long chatId, User user) {
        SendMessage sendMessage = null;

        if (messageText.equals("auto")) {
            CarDTO carDTO = new CarDTO();
            dtoCollector.setCarDTOElement(carDTO);
            dtoCollector.setUserCar(chatId, carDTO.getCarDTOId());
            //user.setUsersSubscribes(new HashMap<>());
            //userCache.updateUserBotState(user, BotState.ASK_CARNAME);
            user.setBotState(BotState.ASK_CARNAME);
            userRepository.save(user);
            return new SendMessage(chatId, "Марку?");
        }
        if (user.getBotState().equals(BotState.ASK_CARNAME)) {

            CarDTO carDTOElement = dtoCollector.getCarDTOElement(chatId);
            dtoCollector.removeCarDTOElement(carDTOElement);
            carDTOElement.setCarName(messageText);
            dtoCollector.setCarDTOElement(carDTOElement);
            user.setBotState(BotState.ASK_MODEL);
            userRepository.save(user);
            //userCache.updateUserBotState(user, BotState.ASK_MODEL);
            return new SendMessage(chatId, "Модель?");
        }
        if (user.getBotState().equals(BotState.ASK_MODEL)) {
            CarDTO carDTOElement = dtoCollector.getCarDTOElement(chatId);
            dtoCollector.removeCarDTOElement(carDTOElement);
            carDTOElement.setCarMark(messageText);
            dtoCollector.setCarDTOElement(carDTOElement);
            user.setBotState(BotState.ASK_PRICE);
            userRepository.save(user);
            //userCache.updateUserBotState(user, BotState.ASK_PRICE);
            return new SendMessage(chatId, "Цена?");

        }
        if(user.getBotState().equals(BotState.ASK_PRICE)) {
            CarDTO carDTOElement = dtoCollector.getCarDTOElement(chatId);
            dtoCollector.removeCarDTOElement(carDTOElement);
            carDTOElement.setCarPrice(messageText);
            dtoCollector.setCarDTOElement(carDTOElement);
            user.setBotState(BotState.ASK_SOMETHING);
            CarCarcase carCarcase = new CarCarcase();
            carCarcase.setCarMark(carDTOElement.getCarMark());
            carCarcase.setCarName(carDTOElement.getCarName());
            carCarcase.setCarPrice(Long.valueOf(carDTOElement.getCarPrice()));

            //userCache.updateUserBotState(user, BotState.ASK_SOMETHING);
            String[] car = new String[3];
            car[0] = carDTOElement.getCarName();
            car[1] = carDTOElement.getCarMark();
            car[2] = carDTOElement.getCarPrice();
            user.addcarItems(carCarcase);
            carCarcase.setUser(user);
            userRepository.save(user);
            try {
                return carParseService.getCarMessage(car, chatId, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sendMessage;
    }


}
