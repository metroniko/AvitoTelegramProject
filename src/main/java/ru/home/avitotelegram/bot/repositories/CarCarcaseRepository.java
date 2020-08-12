package ru.home.avitotelegram.bot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.home.avitotelegram.entity.CarCarcase;

@Repository
public interface CarCarcaseRepository extends CrudRepository<CarCarcase, Long> {
    CarCarcase getCarCarcaseById(Long carCascade);
}
