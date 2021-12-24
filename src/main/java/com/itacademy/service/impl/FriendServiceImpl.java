package com.itacademy.service.impl;

import com.itacademy.entity.FriendEntity;
import com.itacademy.entity.FriendZoneEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.friend_model.FriendModelGet;
import com.itacademy.model.users_models.UserModelGet;
import com.itacademy.repository.FriendRepository;
import com.itacademy.service.FriendService;
import com.itacademy.service.FriendZoneService;
import com.itacademy.service.UsersService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public UserEntity newFriend(UserModelGet model) {
        UserEntity otherFriends = usersService.convertModelToEntity(model);

        if(usersService.getCurrentUser().equals(otherFriends)){
            throw  new IllegalArgumentException("Нельзя себя добавлять в друзья");
        }

        UserEntity itIsMe = usersService.getCurrentUser();

        FriendZoneEntity myFriendZone = zoneService.getFriendZoneByUser(itIsMe);

        List<FriendEntity> friendEntityList = getByFriendZoneEntity(myFriendZone);

        if(isAlreadyThere(friendEntityList, otherFriends)){
            throw new IllegalArgumentException("Уже добавлен в друзья");
        }

        FriendEntity otherFriend = new FriendEntity();
        otherFriend.setFriendZoneEntity(myFriendZone);
        otherFriend.setUserEntity(otherFriends);

        friendRepository.save(otherFriend);

        return itIsMe;
    }

    @Override
    public Boolean isAlreadyThere(List<FriendEntity> friendEntityList, UserEntity friend){

        for(FriendEntity friends : friendEntityList){
            if(friends.getUserEntity().equals(friend)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<FriendModelGet> convertEntityToModelList(List<FriendEntity> entityList){
        List<FriendModelGet> modelGetList = new ArrayList<>();
        for(FriendEntity entity : entityList){
            modelGetList.add(convertEntityToModel(entity));
        }
        return modelGetList;
    }

    @Override
    public FriendModelGet convertEntityToModel(FriendEntity entity){
        FriendModelGet modelGet = new FriendModelGet();
        modelGet.setId(entity.getId());
        modelGet.setUserGet(usersService.convertUserEntityToUserModel(entity.getUserEntity()));
        return modelGet;
    }

    @Override
    public FriendEntity delete(FriendEntity entity){
        friendRepository.delete(entity);
        return entity;
    }

}
