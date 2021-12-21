package com.itacademy.controller;

import com.itacademy.model.users_models.UserModelPost;
import com.itacademy.model.users_models.UserAuthModelPost;
import com.itacademy.model.users_models.UserModelGet;
import com.itacademy.model.users_models.UserUpdateModelPassword;
import com.itacademy.service.UserInfoService;
import com.itacademy.service.impl.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersServiceImpl usersService;
    @Autowired
    private UserInfoService userInfoService;


    @GetMapping("/getAll")
    public List<UserModelGet> userMenu() {
        return usersService.convertUserEntityToUserModel(usersService.getAllUsers());
    }

    @PostMapping("/registration")
    public UserModelGet newUser(@RequestBody UserModelPost userModelPost) {
        return usersService.convertUserEntityToUserModel(usersService.newUser(userModelPost));
    }

    @PostMapping("/sing-in")
    public ResponseEntity<String> sing(@RequestBody UserAuthModelPost userAuthModelPost) throws IllegalAccessException {
        return ResponseEntity.ok(usersService.getAuthorizedToken(userAuthModelPost));
    }

    @GetMapping("/get-current")
    public UserModelGet getCurrent() {
        return usersService.convertUserEntityToUserModel(usersService.getCurrentUser());
    }

    @PostMapping("/update")
    public UserModelGet setUpdateUser(@RequestBody UserUpdateModelPassword userNewPassword) throws IllegalAccessException {
        return usersService.convertUserEntityToUserModel(usersService.updatePassword(userNewPassword));
    }

    @DeleteMapping("/deleteUser")
    public UserModelGet deleteUser() {
        return usersService.convertUserEntityToUserModel(usersService.deleteUser(usersService.getCurrentUser()));
    }

    @GetMapping("/activation/{code}")
    public UserModelGet acivationUser(@PathVariable String code) {
        return usersService.convertUserEntityToUserModel(usersService.activationUser(code));
    }

}