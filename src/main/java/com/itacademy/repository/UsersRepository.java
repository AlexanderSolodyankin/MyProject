package com.itacademy.repository;

import com.itacademy.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByLogin(String login);
    Optional<UserEntity> findByActivationCode(String activationCode);
}
