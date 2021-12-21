package com.itacademy.service.impl;

import com.itacademy.entity.UserInfoEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.usersModels.UserInfoModelGet;
import com.itacademy.model.usersModels.UserInfoModelPost;
import com.itacademy.repository.UserInfoRepository;
import com.itacademy.service.UserInfoService;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UsersService usersService;

    @Override
    public List<UserInfoEntity> getAllUsersInfo() {
        return userInfoRepository.findAll();
    }

    @Override
    public UserInfoEntity save(UserInfoModelPost userInfoModelPost) throws IllegalAccessException {
        UserInfoEntity userInfoEntity = convertModelToEntity(userInfoModelPost);
        userInfoEntity.setUserEntity(usersService.getCurrentUser());
        if (userInfoEntity.getUserEntity() == null) throw new IllegalAccessException(
                "Нельзя сохранить информацию о пользователе если не существует самого пользователя");
        return userInfoRepository.save(userInfoEntity);
    }


    @Override
    public UserInfoEntity delete(UserEntity userEntity) {
        UserInfoEntity userInfoEntity = getUserInfo(userEntity);
        userInfoRepository.delete(userInfoEntity);
        return userInfoEntity;
    }

    @Override
    public UserInfoEntity getUserInfo(UserEntity userEntity) {
        return userInfoRepository.findByUserEntity(userEntity).orElseThrow(
                () -> new IllegalArgumentException("Информации по Пользователю отсутствует")
        );
    }

    @Override
    public UserInfoEntity getUserInfo(Long id) {
        return userInfoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Информации по данному ID номеру отсутствует")
        );
    }

    @Override
    public UserInfoModelGet convertUserEntityToUserModel(UserInfoEntity userInfoEntity) {
        UserInfoModelGet userInfoModelGet = new UserInfoModelGet();
        userInfoModelGet.setId(userInfoEntity.getId());
        userInfoModelGet.setUserModelGet(usersService.convertUserEntityToUserModel(userInfoEntity.getUserEntity()));
        userInfoModelGet.setName(userInfoEntity.getName());
        userInfoModelGet.setSerName(userInfoEntity.getSerName());
        userInfoModelGet.setPatronymic(userInfoEntity.getPatronymic());
        userInfoModelGet.setPhone(userInfoEntity.getPhone());
        userInfoModelGet.setCountry(userInfoEntity.getCountry());
        userInfoModelGet.setCity(userInfoEntity.getCity());
        userInfoModelGet.setCar(userInfoEntity.getCar());
        userInfoModelGet.setGender(userInfoModelGet.isGender());
        return userInfoModelGet;
    }

    @Override
    public List<UserInfoModelGet> convertUserEntityToUserModelList(List<UserInfoEntity> userInfoEntity) {
        List<UserInfoModelGet> userInfoModelGetList = new ArrayList<>();
        for (UserInfoEntity userInfo : userInfoEntity) {
            userInfoModelGetList.add(convertUserEntityToUserModel(userInfo));
        }
        return userInfoModelGetList;
    }

    @Override
    public UserInfoEntity convertModelToEntity(UserInfoModelPost userInfoModelPost) {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setName(userInfoModelPost.getName());
        userInfoEntity.setSerName(userInfoModelPost.getSerName());
        userInfoEntity.setPatronymic(userInfoModelPost.getPatronymic());
        userInfoEntity.setPhone(userInfoModelPost.getPhone());
        userInfoEntity.setCountry(userInfoModelPost.getCountry());
        userInfoEntity.setCity(userInfoModelPost.getCity());
        userInfoEntity.setCar(userInfoModelPost.getCar());
        userInfoEntity.setGender(userInfoModelPost.isGender());
        return userInfoEntity;
    }
}
