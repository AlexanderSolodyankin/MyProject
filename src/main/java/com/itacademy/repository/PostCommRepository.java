package com.itacademy.repository;

import com.itacademy.entity.PostCommentaryEntity;
import com.itacademy.entity.PostUsersEntity;
import com.itacademy.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostCommRepository extends JpaRepository<PostCommentaryEntity, Long> {
    Optional<List<PostCommentaryEntity>> findByPostUsersEntity(PostUsersEntity postUsersEntity);

}
