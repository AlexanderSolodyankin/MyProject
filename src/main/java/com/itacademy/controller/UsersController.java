package com.itacademy.controller;

import com.itacademy.entity.UserEntity;
import com.itacademy.model.usersModels.UserAuthModel;
import com.itacademy.model.usersModels.UserModel;
import com.itacademy.model.usersModels.UserUpdateModelPassword;
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
    public List<UserModel> userMenu() {
        return usersService.convertUserEntityToUserModel(usersService.getAllUsers());
    }

    @PostMapping("/registration")
    public UserModel newUser(@RequestBody UserEntity userEntity) {
        return usersService.convertUserEntityToUserModel(usersService.newUser(userEntity));
    }

    @PostMapping("/sing-in")
    public ResponseEntity<String> sing(@RequestBody UserAuthModel userAuthModel) throws IllegalAccessException {
        return ResponseEntity.ok(usersService.getAuthorizedToken(userAuthModel));
    }

    @GetMapping("/get-current")
    public UserModel getCurrent() {
        return usersService.convertUserEntityToUserModel(usersService.getCurrentUser());
    }

    @PostMapping("/update")
    public UserModel setUpdateUser(@RequestBody UserUpdateModelPassword userNewPassword) throws IllegalAccessException {
        return usersService.convertUserEntityToUserModel(usersService.updatePassword(userNewPassword));
    }

    @DeleteMapping("/deleteUser")
    public UserModel deleteUser() {
        return usersService.convertUserEntityToUserModel(usersService.deleteUser(usersService.getCurrentUser()));
    }

    @GetMapping("/activation/{code}")
    public UserModel acivationUser(@PathVariable String code) {
        return usersService.convertUserEntityToUserModel(usersService.activationUser(code));
    }

}