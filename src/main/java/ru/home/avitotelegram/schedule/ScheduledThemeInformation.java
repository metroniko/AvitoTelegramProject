package ru.home.avitotelegram.schedule;


import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.home.avitotelegram.bot.TelegramBot;

//@Component
@EnableScheduling
public class ScheduledThemeInformation {

    private TelegramBot telegramBot;

    public ScheduledThemeInformation(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Scheduled(fixedRate = 3000)
    public void report() {
        try {
            telegramBot.execute(new SendMessage((long) 417577247, "mess"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
