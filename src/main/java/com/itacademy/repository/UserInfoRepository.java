package com.itacademy.repository;

import com.itacademy.entity.UserInfo;
import com.itacademy.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    Optional<UserInfo> findByUserEntity(UserEntity userEntity);
}
