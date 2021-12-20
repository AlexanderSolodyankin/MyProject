package com.itacademy.service.impl;

import com.itacademy.entity.PostUsers;
import com.itacademy.repository.PostRepository;
import com.itacademy.repository.UsersRepository;
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
    public List<PostUsers> getAll() {
        return postRepository.findAll();
    }

    @Override
    public List<PostUsers> getPostUser() {
        return postRepository.findByUserEntity(usersService.getCurrentUser()).orElse(null);
    }

    @Override
    public PostUsers getPostUserUnit() {
        return null;
    }

    @Override
    public PostUsers deletePost(Long id) {
        return null;
    }

    @Override
    public PostUsers updatePost(PostUsers postUsers) {
        return null;
    }
}
