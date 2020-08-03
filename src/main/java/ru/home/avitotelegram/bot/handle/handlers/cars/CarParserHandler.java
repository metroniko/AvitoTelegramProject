package ru.home.avitotelegram.bot.handle.handlers.cars;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.avitotelegram.avitoparser.ParserAuto;
import ru.home.avitotelegram.bot.botState.BotState;
import ru.home.avitotelegram.bot.handle.handlers.InputMessageHandler;

import static ru.home.avitotelegram.bot.botState.BotState.*;

@Component
public class CarParserHandler implements InputMessageHandler {

    private ParserAuto autoParser;
    private BotState botState = PARSE_AUTO;

    public CarParserHandler(ParserAuto autoParser) {
        this.autoParser = autoParser;
    }

    @Override
    public BotState getHandlerName() {
        return botState;
    }


    /**
     * ЗАГЛУШКА
     * */
    @Override
    public SendMessage handle(Message message) {
        return autoParser.parse();
    }

}
