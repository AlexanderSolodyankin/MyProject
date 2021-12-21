package com.itacademy.service.impl;

import com.itacademy.entity.PostCommentaryEntity;
import com.itacademy.entity.PostUsersEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.post_model.PostCommentaryModelGet;
import com.itacademy.repository.PostCommRepository;
import com.itacademy.service.PostCommentaryService;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PostCommentaryServiceImpl implements PostCommentaryService {
    @Autowired
    private PostCommRepository postCommRepository;
    @Autowired
    private UsersService usersService;

    @Override
    public List<PostCommentaryEntity> getAll() {
        return postCommRepository.findAll();
    }

    @Override
    public List<PostCommentaryEntity> getAllPostUnit(PostUsersEntity postUsersEntity) {
        return postCommRepository.findByPostUsersEntity(postUsersEntity).orElse(null);
    }

    @Override
    public PostCommentaryEntity newCommentary(PostCommentaryEntity postCommentaryEntity) {
        return postCommRepository.save(postCommentaryEntity);
    }

    @Override
    public PostCommentaryEntity getCommentUsers(UserEntity userEntity) {
        return null;
    }

    @Override
    public PostCommentaryEntity deleteComment(PostCommentaryEntity postCommentaryEntity) {
        postCommRepository.delete(postCommentaryEntity);
        return postCommentaryEntity;
    }

    @Override
    public PostCommentaryEntity updateCommentary(PostCommentaryEntity postCommentaryEntity) {
        return newCommentary(postCommentaryEntity);
    }

    @Override
    public PostCommentaryEntity getById(Long id) {
        return postCommRepository.getById(id);
    }

    @Override
    public UserEntity getUsersByPost(PostCommentaryEntity postCommentaryEntity) {
        return postCommentaryEntity.getUserEntity();
    }

    @Override
    public PostCommentaryModelGet convertEntityToModel(PostCommentaryEntity postCommentaryEntity){
        PostCommentaryModelGet postCommentaryModelGet = new PostCommentaryModelGet();
        postCommentaryModelGet.setId(postCommentaryModelGet.getId());
        postCommentaryModelGet.setUserModelGet(usersService.convertUserEntityToUserModel(postCommentaryEntity.getUserEntity()));
        postCommentaryModelGet.setValues(postCommentaryEntity.getValues());
        postCommentaryModelGet.setCreateData(postCommentaryEntity.getCreateData());
        return postCommentaryModelGet;
    }

    @Override
    public List<PostCommentaryModelGet> converEntityToModelList(List<PostCommentaryEntity> postCommentaryEntityList){
        List<PostCommentaryModelGet> postCommentaryModelGetList = new ArrayList<>();
        for(PostCommentaryEntity postCommentaryEntity: postCommentaryEntityList){
            postCommentaryModelGetList.add(convertEntityToModel(postCommentaryEntity));
        }
        return postCommentaryModelGetList;
    }
}
