package com.itacademy.service;

import com.itacademy.entity.UserEntity;
import com.itacademy.model.UserAuthModel;

import java.util.List;

public interface UsersService {
    UserEntity newUser(UserEntity user);
    List<UserEntity> getAllUsers();
    UserEntity getByUserLogin(String login);
    UserEntity getAuthorizet(UserAuthModel userAuthModel);
    UserEntity deleteUser(UserAuthModel userAuthModel);
}
