package ru.home.avitotelegram.bot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.home.avitotelegram.itemInformation.fullItemInformation.CarItem;


@Repository
public interface CarRepository extends CrudRepository<CarItem, Long> {
    CarItem getCarItemById(Long carId);
}
