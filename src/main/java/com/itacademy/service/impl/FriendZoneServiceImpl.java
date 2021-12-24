package com.itacademy.service.impl;

import com.itacademy.entity.FriendEntity;
import com.itacademy.entity.FriendZoneEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.friend_model.FriendModelGet;
import com.itacademy.model.friend_model.FriendZoneModelGet;
import com.itacademy.repository.FriendZoneRepository;
import com.itacademy.service.FriendService;
import com.itacademy.service.FriendZoneService;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendZoneServiceImpl implements FriendZoneService {

    @Autowired
    private FriendZoneRepository friendZoneRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private FriendService friendService;

    @Override
    public List<FriendZoneEntity> getAll() {
        return friendZoneRepository.findAll();
    }

    @Override
    public FriendZoneEntity getFriendZoneByUser(UserEntity entity) {
        return friendZoneRepository.findByUserEntity(entity).orElse(null);
    }

    @Override
    public FriendZoneEntity newFriendZone(FriendZoneEntity entity) {

        return friendZoneRepository.save(entity);
    }

    @Override
    public FriendZoneEntity deleteFriend(FriendEntity entity) {
        return null;
    }

    @Override
    public FriendZoneEntity deleteFriendZone(FriendZoneEntity entity) {
        return null;
    }

    @Override
    public List<FriendZoneModelGet> convertFriendZoneList(List<FriendZoneEntity> entityList) {
        List<FriendZoneModelGet> modelGetList = new ArrayList<>();
        for(FriendZoneEntity entity : entityList){
            modelGetList.add(convertFriendZone(entity));
        }
        return modelGetList;
    }

    @Override
    public FriendZoneModelGet convertFriendZone(FriendZoneEntity entity) {
        FriendZoneModelGet modelGet = new FriendZoneModelGet();
        modelGet.setId(entity.getId());
        modelGet.setUser(usersService.convertUserEntityToUserModel(entity.getUserEntity()));

        List<FriendEntity> friendModelGets = friendService.getByFriendZoneEntity(entity);

        modelGet.setFriends(friendService.convertEntityToModelList(friendModelGets));

        return modelGet;
    }

    @Override
    public FriendZoneEntity delete(FriendZoneEntity entity){
        friendZoneRepository.delete(entity);
        return entity;
    }

    @Override
    public UserEntity deleteFriend(UserEntity deleteFriend){
        UserEntity myAcaunt = usersService.getCurrentUser();
        FriendZoneEntity myZone = getFriendZoneByUser(myAcaunt);
        List<FriendEntity> friendEntityList = friendService.getByFriendZoneEntity(myZone);
        for (FriendEntity friend : friendEntityList){
            if(friend.getUserEntity().equals(deleteFriend)){
                friendService.delete(friend);
            }
        }
        return myAcaunt;
    }

}
