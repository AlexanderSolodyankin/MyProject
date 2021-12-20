package com.itacademy.repository;

import com.itacademy.entity.PostUsers;
import com.itacademy.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostUsers, Long> {
    Optional<List<PostUsers>> findByUserEntity(UserEntity userEntity);
}
