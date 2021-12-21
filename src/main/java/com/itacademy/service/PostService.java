package com.itacademy.service;

import com.itacademy.entity.PostUsersEntity;
import com.itacademy.model.postModel.PostModel;

import java.util.List;

public interface PostService {
    List<PostUsersEntity> getAll();
    List<PostUsersEntity> getPostUser();
    PostUsersEntity getPostUserUnit(String postValues);
    PostUsersEntity deletePost(Long id);
    PostUsersEntity updatePost(PostUsersEntity postUsersEntity);
    PostModel convertEntityToModel(PostUsersEntity postUsersEntity);
    List<PostModel> convertEntityToModelList(List<PostUsersEntity> postUsersEntityList);



}
