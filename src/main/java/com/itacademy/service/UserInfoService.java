package com.itacademy.service;

import com.itacademy.entity.UserInfo;
import com.itacademy.entity.UserEntity;

import java.util.List;

public interface UserInfoService {
    List<UserInfo> getAllUsersInfo();
    UserInfo save(UserInfo userInfo) throws IllegalAccessException;
    UserInfo delete(UserEntity userEntity);
    UserInfo getUserInfo(UserEntity userEntity);
    UserInfo getUserInfo(Long id);
}
