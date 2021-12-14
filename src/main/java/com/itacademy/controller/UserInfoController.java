package com.itacademy.controller;

import com.itacademy.entity.UserInfo;
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
    public List<UserInfo> getAll(){
        return userInfoService.getAllUsersInfo();
    }

    @PostMapping("/setUserInfo")
    public UserInfo setUserInfo(@RequestBody UserInfo userInfo) throws IllegalAccessException {
        userInfo.setUserEntity(usersService.getCurrentUser());
        return userInfoService.save(userInfo);
    }
    @DeleteMapping("/deleteUserInfo")
    public UserInfo deleteUserInfo(){
        return userInfoService.delete(usersService.getCurrentUser());
    }

    @GetMapping("/getUserInfoByUser")
    public UserInfo getUserInfo(){
        return userInfoService.getUserInfo(usersService.getCurrentUser());
    }


    @GetMapping("/getUserInfoById/{id}")
    public UserInfo getUserInfoById(@PathVariable Long id){
        return userInfoService.getUserInfo(id);
    }

}
