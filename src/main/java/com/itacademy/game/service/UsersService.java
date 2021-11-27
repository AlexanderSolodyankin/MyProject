package com.itacademy.game.service;

import com.itacademy.game.entity.UserEntity;

import java.util.List;

public interface UsersService {
    UserEntity newUser(UserEntity user);
    List<UserEntity> getAllUsers();
//    Users getByUserLogin(String login);
}
