package com.itacademy.service.impl;

import com.itacademy.entity.UserInfoEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.usersModels.UserInfoModel;
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
    public UserInfoEntity save(UserInfoEntity userInfoEntity) throws IllegalAccessException {
        UserEntity userSaveInfo = usersService.getCurrentUser();
        userInfoEntity.setUserEntity(userSaveInfo);
        if (userSaveInfo == null) throw new IllegalAccessException(
                "Нельзя сохранить информацию о пользователе если не существует самого пользователя");
        userInfoEntity.setUserEntity(userSaveInfo);
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
    public UserInfoModel convertUserEntityToUserModel(UserInfoEntity userInfoEntity) {
        UserInfoModel userInfoModel = new UserInfoModel();
        userInfoModel.setId(userInfoEntity.getId());
        userInfoModel.setUserModel(usersService.convertUserEntityToUserModel(userInfoEntity.getUserEntity()));
        userInfoModel.setName(userInfoEntity.getName());
        userInfoModel.setSerName(userInfoEntity.getSerName());
        userInfoModel.setPatronymic(userInfoEntity.getPatronymic());
        userInfoModel.setPhone(userInfoEntity.getPhone());
        userInfoModel.setCountry(userInfoEntity.getCountry());
        userInfoModel.setCity(userInfoEntity.getCity());
        userInfoModel.setCar(userInfoEntity.getCar());
        userInfoModel.setGender(userInfoModel.isGender());
        return userInfoModel;
    }

    @Override
    public List<UserInfoModel> convertUserEntityToUserModelList(List<UserInfoEntity> userInfoEntity) {
        List<UserInfoModel> userInfoModelList = new ArrayList<>();
        for (UserInfoEntity userInfo : userInfoEntity) {
            userInfoModelList.add(convertUserEntityToUserModel(userInfo));
        }
        return userInfoModelList;
    }
}
