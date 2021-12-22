package com.itacademy.repository;

import com.itacademy.entity.PublicationCommentaryEntity;
import com.itacademy.entity.PublicationUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PublicationCommRepository extends JpaRepository<PublicationCommentaryEntity, Long> {
    Optional<List<PublicationCommentaryEntity>> findByPostUsersEntity(PublicationUsersEntity publicationUsersEntity);

}
