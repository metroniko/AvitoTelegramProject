package ru.home.avitotelegram.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.home.avitotelegram.bot.repositories.UserRepository;
import ru.home.avitotelegram.bot.services.CarParseService;
import ru.home.avitotelegram.entity.CarCarcase;
import ru.home.avitotelegram.entity.User;

import java.io.IOException;
import java.util.Collection;

@Service
@EnableScheduling
public class ScheduledThemeInformation {

    private CarParseService carParseService;
    private UserRepository userRepository;
    private static  final Logger log = LoggerFactory.getLogger(ScheduledThemeInformation.class);


    public ScheduledThemeInformation(CarParseService carParseService,
                                     UserRepository userRepository) {
        this.carParseService = carParseService;
        this.userRepository = userRepository;
    }


    @Scheduled(fixedRate = 43200000)
    public void report() {
        Iterable<User> users = userRepository.findAll();

        System.out.println("fjgjfjgjfjjajsjdsa");
        users.forEach(el -> {
            log.info("user: {}", el);
            Long userId = el.getUserId();
            Collection<CarCarcase> carItems = el.getCarItems();
            String[] car = new String[3];
            carItems.forEach(carCarcase -> {
                car[0] = carCarcase.getCarName();
                car[1] = carCarcase.getCarMark();
                car[2] = String.valueOf(carCarcase.getCarPrice());
                try {
                     carParseService.getCarMessage(car, userId, false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        });
    }
}
