package com.itacademy.service;

import com.itacademy.entity.FriendEntity;
import com.itacademy.entity.FriendZoneEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.friend_model.FriendModelGet;
import com.itacademy.model.users_models.UserModelGet;

import java.util.List;

public interface FriendService {
    List<FriendEntity> getAll();

    List<FriendEntity> getByFriendZoneEntity(FriendZoneEntity entity);

    UserEntity newFriend(UserModelGet model);

    Boolean isAlreadyThere(List<FriendEntity> friendEntityList, UserEntity friend);


    List<FriendModelGet> convertEntityToModelList(List<FriendEntity> entityList);

    FriendModelGet convertEntityToModel(FriendEntity entity);

    FriendEntity delete(FriendEntity entity);
}
