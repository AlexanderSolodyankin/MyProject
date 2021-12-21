package com.itacademy.controller;

import com.itacademy.entity.PostUsersEntity;
import com.itacademy.model.post_model.PostModelGet;
import com.itacademy.service.PostService;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UsersService usersService;

    @GetMapping("/getAll")
    public List<PostModelGet> getAll() {
        return postService.convertEntityToModelList(postService.getAll());
    }

    @GetMapping("/getAllPostUser")
    public List<PostModelGet> getAllPostUser() {
        return postService.convertEntityToModelList(postService.getPostUserList(usersService.getCurrentUser()));
    }

    @PostMapping("/newPost")
    public PostModelGet newPost(@RequestBody PostUsersEntity postUsersEntity) {
        return postService.convertEntityToModel(postService.newPost(postUsersEntity));
    }

    @GetMapping("/getPost/{id}")
    public PostModelGet getById(@PathVariable Long id){
        return postService.convertEntityToModel(postService.getById(id));
    }
}
