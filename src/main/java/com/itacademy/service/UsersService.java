package com.itacademy.service;

import com.itacademy.entity.UserEntity;
import com.itacademy.model.usersModels.UserAuthModel;
import com.itacademy.model.usersModels.UserModel;
import com.itacademy.model.usersModels.UserUpdateModelPassword;

import java.util.List;

public interface UsersService {
    UserEntity setAdmin(UserEntity admin);
    UserEntity newUser(UserEntity user);
    List<UserEntity> getAllUsers();
    UserEntity getByUser(String login);
    UserEntity getByUser(Long id);
    String getAuthorizedToken(UserAuthModel userAuthModel) throws IllegalAccessException;
    UserEntity deleteUser(UserEntity userEntity);
    UserEntity updatePassword(UserUpdateModelPassword userNewPassword) throws IllegalAccessException;
    UserEntity getCurrentUser();
    UserEntity activationUser(String activation);
    UserModel convertUserEntityToUserModel(UserEntity userEntity);
    List<UserModel> convertUserEntityToUserModel(List<UserEntity> userEntity);
}
