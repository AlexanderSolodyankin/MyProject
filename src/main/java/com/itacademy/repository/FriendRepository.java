package com.itacademy.repository;

import com.itacademy.entity.FriendEntity;
import com.itacademy.entity.FriendZoneEntity;
import com.itacademy.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<FriendEntity, Long> {
    Optional<List<FriendEntity>> findByFriendZoneEntity(FriendZoneEntity friendZoneEntity);

    Optional<FriendEntity> findByUserEntity(UserEntity userEntity);
}
