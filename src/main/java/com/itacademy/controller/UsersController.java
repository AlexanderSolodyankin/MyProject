package com.itacademy.controller;

import com.itacademy.entity.UserEntity;
import com.itacademy.model.UserAuthModel;
import com.itacademy.service.UserInfoService;
import com.itacademy.service.impl.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UsersServiceImpl usersService;
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/getAll")
    public List<UserEntity> userMenu() {
        return usersService.getAllUsers();
    }

    @PostMapping("/registration")
    public UserEntity newUser(@RequestBody UserEntity userEntity) throws IllegalAccessException {
      if(usersService.getByUser(userEntity.getLogin()) != null){
        throw  new IllegalAccessException("Такой пользователь уже есть");
      }else return usersService.newUser(userEntity);
    }


    @PostMapping("/log-in")
    public UserEntity getUser(@RequestBody UserAuthModel userAuthModel) {
        return usersService.getAuthorized(userAuthModel);

    }

    @PostMapping("/update")
    public UserEntity setUpdateUser(@RequestBody UserAuthModel userAuthModel, @RequestParam String newPassword) {
        return usersService.updatePassword(userAuthModel, newPassword);
    }

    @DeleteMapping("/deleteUser")
    public UserEntity deleteUser(@RequestBody UserAuthModel userAuthModel) throws IllegalAccessException {
        return usersService.deleteUser(userAuthModel);
    }
    @PostMapping("/getUserById")
    public UserEntity getById(@RequestBody Long id){
        return usersService.getByUser(id);
    }


}
