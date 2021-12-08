package com.itacademy.controller;

import com.itacademy.entity.UserEntity;
import com.itacademy.entity.UserInfo;
import com.itacademy.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/getAll")
    public List<UserInfo> getAll(){
        return userInfoService.getAllUsersInfo();
    }
    @PostMapping("/setUserInfo")
    public UserInfo setUserInfo(@RequestBody UserInfo userInfo) throws IllegalAccessException {
        return userInfoService.save(userInfo);
    }
    @PostMapping("/delete-user-info")
    public UserInfo deleteUserInfo(@RequestBody UserEntity userEntity){
        return userInfoService.delete(userEntity);
    }
    @PostMapping("/getUserInfoByUserLogin")
    public UserInfo getUserInfo(@RequestBody  UserEntity userEntity){
        return userInfoService.getUserInfo(userEntity);
    }
    @PostMapping("/getUserInfoById")
    public UserInfo getUserInfoById(@RequestParam Long id){
        return userInfoService.getUserInfo(id);
    }

}
