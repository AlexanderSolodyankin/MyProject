package com.itacademy.service;

import com.itacademy.entity.UserEntity;
import com.itacademy.model.UserAuthModel;

import java.util.List;

public interface UsersService {
    UserEntity setAdmin(UserEntity admin);
    UserEntity newUser(UserEntity user);
    List<UserEntity> getAllUsers();
    UserEntity getByUser(String login);
    UserEntity getByUser(Long id);
    String getAuthorizedToken(UserAuthModel userAuthModel) throws IllegalAccessException;
    UserEntity deleteUser(UserEntity userEntity);
    UserEntity updatePassword(UserAuthModel userAuthModel, String newPassword);
     UserEntity getCurrentUser();
}
