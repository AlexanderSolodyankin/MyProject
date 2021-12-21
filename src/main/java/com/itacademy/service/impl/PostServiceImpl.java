package com.itacademy.service.impl;

import com.itacademy.entity.PostUsersEntity;
import com.itacademy.model.postModel.PostModel;
import com.itacademy.repository.PostRepository;
import com.itacademy.service.PostService;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsersService usersService;

    @Override
    public List<PostUsersEntity> getAll() {
        return postRepository.findAll();
    }

    @Override
    public List<PostUsersEntity> getPostUser() {
        return postRepository.findByUserEntity(usersService.getCurrentUser()).orElse(null);
    }

    @Override
    public PostUsersEntity getPostUserUnit(String postValues) {
        return postRepository.findByValues(postValues).orElseThrow( () ->
                new IllegalArgumentException("Такого поста не существует"));
    }

    @Override
    public PostUsersEntity deletePost(Long id) {
        return postRepository.getById(id);
    }

    @Override
    public PostUsersEntity updatePost(PostUsersEntity postUsersEntity) {
        postUsersEntity.setUserEntity(usersService.getCurrentUser());
        return postRepository.save(postUsersEntity);
    }

    @Override
    public PostModel convertEntityToModel(PostUsersEntity postUsersEntity) {
        return null;
    }

    @Override
    public List<PostModel> convertEntityToModelList(List<PostUsersEntity> postUsersEntityList) {
        return null;
    }
}
