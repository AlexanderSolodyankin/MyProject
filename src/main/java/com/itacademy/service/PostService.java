package com.itacademy.service;


import com.itacademy.entity.PostUsersEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.postModel.PostModel;

import java.util.List;

public interface PostService {
    List<PostUsersEntity> getAll();

    List<PostUsersEntity> getPostUserList(UserEntity userEntity);

    PostUsersEntity newPost(PostUsersEntity postUsersEntity);

    PostUsersEntity getByValues(String postValues);

    PostUsersEntity deletePost(Long id);

    PostUsersEntity getById(Long id);

    PostUsersEntity updatePost(PostUsersEntity postUsersEntity);

    PostModel convertEntityToModel(PostUsersEntity postUsersEntity);

    List<PostModel> convertEntityToModelList(List<PostUsersEntity> postUsersEntityList);


}
