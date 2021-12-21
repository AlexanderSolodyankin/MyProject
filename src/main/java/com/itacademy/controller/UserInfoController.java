package com.itacademy.controller;

import com.itacademy.model.usersModels.UserInfoModelGet;
import com.itacademy.model.usersModels.UserInfoModelPost;
import com.itacademy.service.UserInfoService;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UsersService usersService;

    @GetMapping("/getAll")
    public List<UserInfoModelGet> getAll() {
        return userInfoService.convertUserEntityToUserModelList(userInfoService.getAllUsersInfo());
    }

    @PostMapping("/setUserInfo")
    public UserInfoModelGet setUserInfo(@RequestBody UserInfoModelPost userInfoModelPost) throws IllegalAccessException {
        return userInfoService.convertUserEntityToUserModel(userInfoService.save(userInfoModelPost));
    }

    @DeleteMapping("/deleteUserInfo")
    public UserInfoModelGet deleteUserInfo() {
        return userInfoService.convertUserEntityToUserModel(
                userInfoService.delete(usersService.getCurrentUser()));
    }

    @GetMapping("/getUserInfoByUser")
    public UserInfoModelGet getUserInfo() {
        return userInfoService.convertUserEntityToUserModel(
                userInfoService.getUserInfo(usersService.getCurrentUser()));
    }


    @GetMapping("/getUserInfoById/{id}")
    public UserInfoModelGet getUserInfoById(@PathVariable Long id) {
        return userInfoService.convertUserEntityToUserModel(userInfoService.getUserInfo(id));
    }

}
