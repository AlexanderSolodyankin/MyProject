package com.itacademy.service;

import com.itacademy.entity.UserInfoEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.users_models.UserInfoModelGet;
import com.itacademy.model.users_models.UserInfoModelPost;

import java.util.List;

public interface UserInfoService {
    List<UserInfoEntity> getAllUsersInfo();

    UserInfoEntity save(UserInfoModelPost userInfoModelPost) throws IllegalAccessException;

    UserInfoEntity delete(UserEntity userEntity);

    UserInfoEntity getUserInfo(UserEntity userEntity);

    UserInfoEntity getUserInfo(Long id);

    UserInfoModelGet convertUserEntityToUserModel(UserInfoEntity userInfoEntity);

    List<UserInfoModelGet> convertUserEntityToUserModelList(List<UserInfoEntity> userInfoEntity);

    UserInfoEntity convertModelToEntity(UserInfoModelPost userInfoModelPost);
}
