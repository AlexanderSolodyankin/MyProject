package com.itacademy.controller;

import com.itacademy.entity.UserEntity;
import com.itacademy.entity.UserInfo;
import com.itacademy.model.UserAuthModel;
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
    @DeleteMapping("/deleteUserInfo")
    public UserInfo deleteUserInfo(@RequestBody UserAuthModel userEntity){
        return userInfoService.delete(userEntity);
    }
    @PostMapping("/getUserInfoByUserLogin")
    public UserInfo getUserInfo(@RequestBody UserAuthModel userEntity){
        return userInfoService.getUserInfo(userEntity);
    }
    @GetMapping("/getUserInfoById/{id}")
    public UserInfo getUserInfoById(@PathVariable Long id){
        return userInfoService.getUserInfo(id);
    }

}
