package com.itacademy.service;

import com.itacademy.entity.PublicationCommentaryEntity;
import com.itacademy.entity.PublicationUsersEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.post_model.PublicationCommentaryModelGet;
import com.itacademy.model.post_model.PublicationModelGet;
import com.itacademy.model.post_model.PublicationModelPost;

import java.util.List;

public interface PublicationCommentaryService {
    List<PublicationCommentaryEntity> getAll();

    List<PublicationCommentaryEntity> getAllPostUnit(PublicationUsersEntity publicationUsersEntity);

    PublicationCommentaryEntity newCommentary(PublicationModelPost post, Long id);

    PublicationCommentaryEntity getCommentUsers(UserEntity userEntity);

    PublicationCommentaryEntity deleteComment(PublicationCommentaryEntity publicationCommentaryEntity);

    PublicationCommentaryEntity updateCommentary(PublicationModelGet get);

    PublicationCommentaryEntity getById(Long id);

    UserEntity getUsersByPost(PublicationCommentaryEntity publicationCommentaryEntity);

    PublicationCommentaryModelGet convertEntityToModel(PublicationCommentaryEntity publicationCommentaryEntity);

    List<PublicationCommentaryModelGet> converEntityToModelList(
            List<PublicationCommentaryEntity> publicationCommentaryEntityList);

    PublicationCommentaryEntity convertModelToEntity(PublicationModelPost post);


}
