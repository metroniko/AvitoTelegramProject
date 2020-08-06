package ru.home.avitotelegram.bot.facade;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.avitotelegram.bot.botState.BotState;
import ru.home.avitotelegram.bot.cache.UserCache;
import ru.home.avitotelegram.bot.handle.BotHandlerContext;
import ru.home.avitotelegram.bot.handle.handlers.InputMessageHandler;
import ru.home.avitotelegram.entity.User;

@Controller
public class TelegramFacade {

    private BotHandlerContext handlerContext;
    private UserCache userCache;
    private static  final Logger log = LoggerFactory.getLogger(TelegramFacade.class);




    public TelegramFacade(BotHandlerContext handlerContext, UserCache userCache ) {
        this.handlerContext = handlerContext;
        this.userCache = userCache;
    }
    public SendMessage handleMessage(Update update) {


        Message message = update.getMessage();
        SendMessage sendMessage = null;
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

    private SendMessage handleInputMessage(Message message) {

        BotState botState = null;

        if (message.getText().equals("/start")) {
            User newUser = new User(message.getChatId());
            log.info("handleInputMessage new User = {}", newUser);
            botState = BotState.HELLO;
            newUser.setBotState(botState);
            userCache.addUser(newUser);

            InputMessageHandler currentHandler = handlerContext.getCurrentHandler(botState);
            return currentHandler.handle(message);

        }
        InputMessageHandler currentHandler = handlerContext.findCurrentContext(message);

        return currentHandler.handle(message);

    }

}
