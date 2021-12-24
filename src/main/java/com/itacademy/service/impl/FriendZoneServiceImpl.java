package com.itacademy.service.impl;

import com.itacademy.entity.FriendEntity;
import com.itacademy.entity.FriendZoneEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.repository.FriendZoneRepository;
import com.itacademy.service.FriendZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendZoneServiceImpl implements FriendZoneService {

    @Autowired
    private FriendZoneRepository friendZoneRepository;

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
}
