package com.itacademy.service;

import com.itacademy.entity.FriendEntity;
import com.itacademy.entity.FriendZoneEntity;
import com.itacademy.model.users_models.UserModelGet;

import java.util.List;

public interface FriendService {
    List<FriendEntity> getAll();

    List<FriendEntity> getByFriendZoneEntity(FriendZoneEntity entity);

    Boolean newFriend(UserModelGet model);


}
