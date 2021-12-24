package com.itacademy.repository;

import com.itacademy.entity.FriendZoneEntity;
import com.itacademy.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendZoneRepository extends JpaRepository<FriendZoneEntity, Long> {
    Optional<FriendZoneEntity> findByUserEntity(UserEntity userEntity);

}
