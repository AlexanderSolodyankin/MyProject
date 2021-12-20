package com.itacademy.service;

import com.itacademy.entity.UserInfoEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.usersModels.UserInfoModel;
import com.itacademy.model.usersModels.UserModel;

import java.util.List;

public interface UserInfoService {
    List<UserInfoEntity> getAllUsersInfo();
    UserInfoEntity save(UserInfoEntity userInfoEntity) throws IllegalAccessException;
    UserInfoEntity delete(UserEntity userEntity);
    UserInfoEntity getUserInfo(UserEntity userEntity);
    UserInfoEntity getUserInfo(Long id);
    UserInfoModel convertUserEntityToUserModel(UserInfoEntity userInfoEntity);
    List<UserInfoModel> convertUserEntityToUserModelList(List<UserInfoEntity> userInfoEntity);
}
