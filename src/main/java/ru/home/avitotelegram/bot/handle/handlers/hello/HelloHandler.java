package ru.home.avitotelegram.bot.handle.handlers.hello;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.home.avitotelegram.bot.botState.BotState;
import ru.home.avitotelegram.bot.cache.UserCache;
import ru.home.avitotelegram.bot.handle.handlers.InputMessageHandler;
import ru.home.avitotelegram.bot.repositories.UserRepository;
import ru.home.avitotelegram.entity.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class HelloHandler implements InputMessageHandler {

    private UserCache userCache;
    private UserRepository userRepository;

    public HelloHandler(UserCache userCache, UserRepository userRepository) {
        this.userCache = userCache;
        this.userRepository = userRepository;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.HELLO;
    }

    @Override
    public SendMessage handle(Message message) {
        long chatId = message.getChatId();
        User user = userRepository.getUserByUserId(chatId);
        //User user = userCache.getUserById(chatId);
        //userCache.updateUserBotState(user, BotState.ASK_SOMETHING);
        user.setBotState(BotState.ASK_SOMETHING);
        userRepository.save(user);

        SendMessage sendMessage = new SendMessage(chatId, "Давай подписываться");
        sendMessage.setReplyMarkup(getInlineMessageKeyboard());
        return sendMessage;
    }

    private InlineKeyboardMarkup getInlineMessageKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonYes = new InlineKeyboardButton().setText("auto");
        InlineKeyboardButton buttonNo = new InlineKeyboardButton().setText("что то ещё");

        //Every button must have callBackData, or else not work !
        buttonYes.setCallbackData("auto");
        buttonNo.setCallbackData("somethingElse");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonYes);
        keyboardButtonsRow1.add(buttonNo);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;

    }
}
