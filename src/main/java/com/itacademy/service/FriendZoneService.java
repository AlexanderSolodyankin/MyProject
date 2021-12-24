package com.itacademy.service;

import com.itacademy.entity.FriendEntity;
import com.itacademy.entity.FriendZoneEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.friend_model.FriendZoneModelGet;

import java.util.List;

public interface FriendZoneService {
    List<FriendZoneEntity> getAll();

    FriendZoneEntity getFriendZoneByUser(UserEntity entity);

    FriendZoneEntity newFriendZone(FriendZoneEntity entity);

    FriendZoneEntity deleteFriend(FriendEntity entity);

    FriendZoneEntity deleteFriendZone(FriendZoneEntity entity);

    List<FriendZoneModelGet> convertFriendZoneList(List<FriendZoneEntity> entityList);

    FriendZoneModelGet convertFriendZone(FriendZoneEntity entity);

    FriendZoneEntity delete(FriendZoneEntity entity);

    UserEntity deleteFriend(UserEntity deleteFriend);
}
