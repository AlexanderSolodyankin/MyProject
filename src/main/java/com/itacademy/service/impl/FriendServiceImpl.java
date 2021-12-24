package com.itacademy.service.impl;

import com.itacademy.entity.FriendEntity;
import com.itacademy.entity.FriendZoneEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.users_models.UserModelGet;
import com.itacademy.repository.FriendRepository;
import com.itacademy.service.FriendService;
import com.itacademy.service.FriendZoneService;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private FriendZoneService zoneService;

    @Autowired
    private UsersService usersService;

    @Override
    public List<FriendEntity> getAll() {
        return friendRepository.findAll();
    }

    @Override
    public List<FriendEntity> getByFriendZoneEntity(FriendZoneEntity entity) {
        return friendRepository.findByFriendZoneEntity(entity).orElse(null);
    }

    @Override
    public Boolean newFriend(UserModelGet model) {
        UserEntity otherFriends = usersService.convertModelToEntity(model);

        if(usersService.getCurrentUser().equals(otherFriends)){
            throw  new IllegalArgumentException("Нельзя себя добавлять в друзья");
        }

        FriendZoneEntity otherFriendsZone = zoneService.getFriendZoneByUser(otherFriends);
        FriendEntity meFriend = new FriendEntity();
        meFriend.setFriendZoneEntity(otherFriendsZone);
        meFriend.setUserEntity(usersService.getCurrentUser());

        FriendZoneEntity myFriendZone = zoneService.getFriendZoneByUser(usersService.getCurrentUser());
        FriendEntity otherFriend = new FriendEntity();
        otherFriend.setFriendZoneEntity(myFriendZone);
        otherFriend.setUserEntity(otherFriends);

        friendRepository.save(meFriend);
        friendRepository.save(otherFriend);

        return true;
    }
}
