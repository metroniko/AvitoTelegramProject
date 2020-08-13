package ru.home.avitotelegram.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
@EnableScheduling
public class ScheduleHeroku {

    private static  final Logger log = LoggerFactory.getLogger(ScheduleHeroku.class);

    @Scheduled(fixedRate = 1200000)
    public void pingHeroku() {
        try {
            URL url = new URL("https://www.google.ru/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            log.info("Ping connection host: {}, response cod: {}", url.getHost(), connection.getResponseCode());
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
