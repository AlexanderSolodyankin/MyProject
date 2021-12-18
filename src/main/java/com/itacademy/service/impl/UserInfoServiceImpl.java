package com.itacademy.service.impl;

import com.itacademy.entity.UserInfo;
import com.itacademy.entity.UserEntity;
import com.itacademy.repository.UserInfoRepository;
import com.itacademy.service.UserInfoService;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UsersService usersService;

    @Override
    public List<UserInfo> getAllUsersInfo() {
        return userInfoRepository.findAll();
    }

    @Override
    public UserInfo save(UserInfo userInfo) throws IllegalAccessException {
        UserEntity userSaveInfo = usersService.getByUser(userInfo.getUserEntity().getLogin());
        userInfo.setUserEntity(userSaveInfo);
        if(userSaveInfo == null) throw new IllegalAccessException(
                "Нельзя сохранить информацию о пользователе если не существует самого пользователя");

        return userInfoRepository.save(userInfo);
    }



    @Override
    public UserInfo delete(UserEntity userEntity) {
        UserInfo userInfo = getUserInfo(userEntity);
        userInfoRepository.delete(userInfo);
        return userInfo;
    }

    @Override
    public UserInfo getUserInfo(UserEntity userEntity) {
        return userInfoRepository.findByUserEntity(userEntity).orElseThrow(
                () -> new IllegalArgumentException("Информации по Пользователю отсутствует")
        );
    }
    @Override
    public UserInfo getUserInfo(Long id) {
        return userInfoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Информации по данному ID номеру отсутствует")
        );
    }
}
