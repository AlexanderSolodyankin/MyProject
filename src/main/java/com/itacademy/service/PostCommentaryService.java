package com.itacademy.service;

import com.itacademy.entity.PostCommentaryEntity;
import com.itacademy.entity.PostUsersEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.post_model.PostCommentaryModelGet;

import java.util.List;

public interface PostCommentaryService {
    List<PostCommentaryEntity> getAll();

    List<PostCommentaryEntity> getAllPostUnit(PostUsersEntity postUsersEntity);

    PostCommentaryEntity newCommentary(PostCommentaryEntity postCommentaryEntity);

    PostCommentaryEntity getCommentUsers(UserEntity userEntity);

    PostCommentaryEntity deleteComment(PostCommentaryEntity postCommentaryEntity);

    PostCommentaryEntity updateCommentary(PostCommentaryEntity postCommentaryEntity);

    PostCommentaryEntity getById(Long id);

    UserEntity getUsersByPost(PostCommentaryEntity postCommentaryEntity);

    PostCommentaryModelGet convertEntityToModel(PostCommentaryEntity postCommentaryEntity);

    List<PostCommentaryModelGet> converEntityToModelList(List<PostCommentaryEntity> postCommentaryEntityList);


}
