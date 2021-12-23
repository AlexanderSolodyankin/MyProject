package com.itacademy.repository;

import com.itacademy.entity.ExpertEntity;
import com.itacademy.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpertRepository extends JpaRepository<ExpertEntity, Long> {
    Optional<List<ExpertEntity>> findByUserEntity(UserEntity userEntity);
}
