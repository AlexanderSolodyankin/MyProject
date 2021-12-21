package com.itacademy.controller;

import com.itacademy.entity.UserInfoEntity;
import com.itacademy.model.usersModels.UserInfoModel;
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
    public List<UserInfoModel> getAll() {
        return userInfoService.convertUserEntityToUserModelList(userInfoService.getAllUsersInfo());
    }

    @PostMapping("/setUserInfo")
    public UserInfoModel setUserInfo(@RequestBody UserInfoEntity userInfoEntity) throws IllegalAccessException {
        return userInfoService.convertUserEntityToUserModel(userInfoService.save(userInfoEntity));
    }

    @DeleteMapping("/deleteUserInfo")
    public UserInfoModel deleteUserInfo() {
        return userInfoService.convertUserEntityToUserModel(
                userInfoService.delete(usersService.getCurrentUser()));
    }

    @GetMapping("/getUserInfoByUser")
    public UserInfoModel getUserInfo() {
        return userInfoService.convertUserEntityToUserModel(
                userInfoService.getUserInfo(usersService.getCurrentUser()));
    }


    @GetMapping("/getUserInfoById/{id}")
    public UserInfoModel getUserInfoById(@PathVariable Long id) {
        return userInfoService.convertUserEntityToUserModel(userInfoService.getUserInfo(id));
    }

}
