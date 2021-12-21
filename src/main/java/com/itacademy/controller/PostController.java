package com.itacademy.controller;

import com.itacademy.entity.PostUsersEntity;
import com.itacademy.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/getAll")
    public List<PostUsersEntity> getAll(){
        return postService.getAll();
    }
}
