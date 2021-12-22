package com.itacademy.repository;

import com.itacademy.entity.PublicationUsersEntity;
import com.itacademy.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PublicationRepository extends JpaRepository<PublicationUsersEntity, Long> {
    Optional<List<PublicationUsersEntity>> findByUserEntity(UserEntity userEntity);

    Optional<PublicationUsersEntity> findByPostValue(String postValues);
}
