package com.itacademy.service;


import com.itacademy.entity.PublicationUsersEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.post_model.PublicationModelGet;
import com.itacademy.model.post_model.PublicationModelPost;

import java.util.List;

public interface PublicationService {
    List<PublicationUsersEntity> getAll();

    List<PublicationUsersEntity> getPostUserList(UserEntity userEntity);

    PublicationUsersEntity newPost(PublicationModelPost post);

    PublicationUsersEntity getByValues(String postValues);

    PublicationUsersEntity deletePost(Long id);

    PublicationUsersEntity getById(Long id);

    PublicationUsersEntity updatePost(PublicationModelGet get);

    PublicationModelGet convertEntityToModel(PublicationUsersEntity publicationUsersEntity);

    List<PublicationModelGet> convertEntityToModelList(List<PublicationUsersEntity> publicationUsersEntityList);

    PublicationUsersEntity convertModelToEntity(PublicationModelPost post);

    PublicationUsersEntity convertModelToEntity(PublicationModelGet post);


    List<PublicationUsersEntity> newsFeed();
}
