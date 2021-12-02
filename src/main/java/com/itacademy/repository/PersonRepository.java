package com.itacademy.repository;

import com.itacademy.entity.Person;
import com.itacademy.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Long> {
    Optional<Person> findByUserEntity(UserEntity userEntity);
}
