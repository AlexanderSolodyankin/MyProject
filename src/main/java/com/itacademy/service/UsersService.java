package com.itacademy.service;

import com.itacademy.entity.UserEntity;
import com.itacademy.model.users_models.UserModelPost;
import com.itacademy.model.users_models.UserAuthModelPost;
import com.itacademy.model.users_models.UserModelGet;
import com.itacademy.model.users_models.UserUpdateModelPassword;

import java.util.List;

public interface UsersService {

    UserEntity newUser(UserModelPost userModelPost);

    List<UserEntity> getAllUsers();

    UserEntity getByUser(String login);

    UserEntity getByUser(Long id);

    String getAuthorizedToken(UserAuthModelPost userAuthModelPost) throws IllegalAccessException;

    UserEntity deleteUser(UserEntity userEntity);

    UserEntity updatePassword(UserUpdateModelPassword userNewPassword) throws IllegalAccessException;

    UserEntity getCurrentUser();

    UserEntity activationUser(String activation);

    UserModelGet convertUserEntityToUserModel(UserEntity userEntity);

    List<UserModelGet> convertUserEntityToUserModel(List<UserEntity> userEntity);

    UserEntity convertModelToEntity(UserModelPost userModelPost);


}
