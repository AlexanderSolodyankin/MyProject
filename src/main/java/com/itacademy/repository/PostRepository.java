package com.itacademy.repository;

import com.itacademy.entity.PostUsersEntity;
import com.itacademy.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostUsersEntity, Long> {
    Optional<List<PostUsersEntity>> findByUserEntity(UserEntity userEntity);
    Optional<PostUsersEntity> findByValues(String postValues);
}
