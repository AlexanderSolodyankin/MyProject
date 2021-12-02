package com.itacademy.controller;

import com.itacademy.entity.UserEntity;
import com.itacademy.model.UserAuthModel;
import com.itacademy.service.impl.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UsersServiceImpl usersService;

    @GetMapping("/getAll")
    public List<UserEntity> userMenu(Model model){
        return usersService.getAllUsers();
    }

    @PostMapping("/registration")
    public UserEntity newUser(@RequestBody UserEntity user){
        user= usersService.newUser(user);
        return user;
    }

    @PostMapping("/log-in")
    public UserEntity getUser(@RequestBody UserAuthModel userAuthModel){
        return usersService.getAuthorizet(userAuthModel);
    }

}
