package com.itacademy.service.impl;

import com.itacademy.entity.UserInfo;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.UserAuthModel;
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
                "Нельзя сохранить информацию о пользователе если не существует Юзера");

        return userInfoRepository.save(userInfo);
    }



    @Override
    public UserInfo delete(UserAuthModel userEntity) {
        UserInfo userInfo = getUserInfo(userEntity);
        userInfoRepository.delete(userInfo);
        return userInfo;
    }

    @Override
    public UserInfo getUserInfo(UserAuthModel userAuthModel) {
        return userInfoRepository.findByUserEntity(usersService.getAuthorized(userAuthModel)).orElse(null);

    }
    @Override
    public UserInfo getUserInfo(Long id) {
        return userInfoRepository.findById(id).orElse(null);
    }
}
