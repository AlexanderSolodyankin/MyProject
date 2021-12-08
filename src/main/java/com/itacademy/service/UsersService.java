package com.itacademy.service;

import com.itacademy.entity.UserEntity;
import com.itacademy.model.UserAuthModel;

import java.util.List;

public interface UsersService {
    UserEntity newUser(UserEntity user);
    List<UserEntity> getAllUsers();
    UserEntity getByUser(String login);
    UserEntity getAuthorized(UserAuthModel userAuthModel);
    UserEntity deleteUser(UserAuthModel userAuthModel) throws IllegalAccessException;
    UserEntity getByUser(Long id);
    UserEntity updatePassword(UserAuthModel userAuthModel, String newPassword);
}
