package com.itacademy.game.controller.user;

import com.itacademy.game.entity.UserEntity;
import com.itacademy.game.service.impl.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UsersServiceImpl usersService;

    @GetMapping
    public List<UserEntity> userMenu(Model model){
        return usersService.getAllUsers();
    }

    @PostMapping
    public UserEntity newUser(@RequestBody UserEntity user){
        user= usersService.newUser(user);
        return user;
    }

}
