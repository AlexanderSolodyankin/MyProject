package com.itacademy.service;

import com.itacademy.entity.PostUsers;

import java.util.List;

public interface PostService {
    List<PostUsers> getAll();
    List<PostUsers> getPostUser();
    PostUsers getPostUserUnit();
    PostUsers deletePost(Long id);
    PostUsers updatePost(PostUsers postUsers);


}
