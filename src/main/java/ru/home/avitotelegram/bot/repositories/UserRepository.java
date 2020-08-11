package ru.home.avitotelegram.bot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.home.avitotelegram.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User getUserByUserId(Long userId);
}
