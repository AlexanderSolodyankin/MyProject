package com.itacademy.service;

import com.itacademy.entity.UserInfo;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.UserAuthModel;

import java.util.List;

public interface UserInfoService {
    List<UserInfo> getAllUsersInfo();
    UserInfo save(UserInfo userInfo) throws IllegalAccessException;
    UserInfo delete(UserAuthModel userEntity);
    UserInfo getUserInfo(UserAuthModel userAuthModel);
    UserInfo getUserInfo(Long id);
}
