package ru.home.avitotelegram.bot.handle.handlers.cars;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.home.avitotelegram.avitoparser.ParserAuto;
import ru.home.avitotelegram.bot.botState.BotState;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.avitotelegram.bot.handle.handlers.InputMessageHandler;

import static ru.home.avitotelegram.bot.botState.BotState.SUBSCRIBE_CAR;

@Component
public class CarHandler implements InputMessageHandler {

    private ParserAuto autoParser;
    private BotState botState = SUBSCRIBE_CAR;

    public CarHandler(ParserAuto autoParser) {
        this.autoParser = autoParser;
    }

    @Override
    public BotState getHandlerName() {
        return SUBSCRIBE_CAR;
    }


    /**
     * ЗАГЛУШКА
     *
     *
     * */
    @Override
    public SendMessage handle(Message message) {
        return autoParser.parse();
    }

}
