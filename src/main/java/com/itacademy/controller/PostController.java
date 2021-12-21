package com.itacademy.controller;

import com.itacademy.entity.PostUsersEntity;
import com.itacademy.model.postModel.PostModel;
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
    public List<PostModel> getAll() {
        return postService.convertEntityToModelList(postService.getAll());
    }

    @GetMapping("/getAllPostUser")
    public List<PostModel> getAllPostUser() {
        return postService.convertEntityToModelList(postService.getPostUserList(usersService.getCurrentUser()));
    }

    @PostMapping("/newPost")
    public PostModel newPost(@RequestBody PostUsersEntity postUsersEntity) {
        return postService.convertEntityToModel(postService.newPost(postUsersEntity));
    }

    @GetMapping("/getPost/{id}")
    public PostModel getById(@PathVariable Long id){
        return postService.convertEntityToModel(postService.getById(id));
    }
}
