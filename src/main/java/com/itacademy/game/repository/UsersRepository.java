package com.itacademy.game.repository;

import com.itacademy.game.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UserEntity,Long> {
//    Optional<Users> findByUserLogin(String login);
}
