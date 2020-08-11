package ru.home.avitotelegram.bot.facade;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.avitotelegram.bot.botState.BotState;
import ru.home.avitotelegram.bot.cache.UserCache;
import ru.home.avitotelegram.bot.handle.BotHandlerContext;
import ru.home.avitotelegram.bot.handle.handlers.InputMessageHandler;
import ru.home.avitotelegram.bot.handle.handlers.askSomething.AskSomethingHandler;
import ru.home.avitotelegram.bot.repositories.UserRepository;
import ru.home.avitotelegram.entity.User;

@Controller
public class TelegramFacade {

    private BotHandlerContext handlerContext;
    private UserCache userCache;
    private static  final Logger log = LoggerFactory.getLogger(TelegramFacade.class);
    private UserRepository userRepository;



    public TelegramFacade(BotHandlerContext handlerContext,
                          UserCache userCache, UserRepository userRepository) {
        this.handlerContext = handlerContext;
        this.userCache = userCache;
        this.userRepository = userRepository;
    }
    public BotApiMethod<?> handleMessage(Update update) {

        if (update.hasCallbackQuery()) {
            Message message1 = update.getCallbackQuery().getMessage();
            AskSomethingHandler currentContext = (AskSomethingHandler) handlerContext.findCurrentContext(message1);
            return currentContext.handleCallbackQuery(update.getCallbackQuery());
        }

        Message message = update.getMessage();
        BotApiMethod<?> sendMessage = null;
        long chatId = message.getFrom().getId();
        String messageText = message.getText();
        if (message.hasText()) {
            log.info("New Message from user: User {}, chatId {}, text: {}",
                    message.getFrom().getUserName(),
                    chatId,
                    messageText);
            sendMessage = handleInputMessage(message);
        }
        return sendMessage;

    }

    private BotApiMethod<?> handleInputMessage(Message message) {

        BotState botState;

        if (message.getText().equals("/start")) {

            User newUser = new User(message.getChatId());
            log.info("handleInputMessage new User = {}", newUser);
            botState = BotState.HELLO;
            newUser.setBotState(botState);
            userRepository.save(newUser);

            //старая реализация
            //userCache.addUser(newUser);

            InputMessageHandler currentHandler = handlerContext.getCurrentHandler(botState);
            return currentHandler.handle(message);

        }
        InputMessageHandler currentHandler = handlerContext.findCurrentContext(message);

        return currentHandler.handle(message);

    }

}
