package com.itacademy.controller;

import com.itacademy.entity.FriendEntity;
import com.itacademy.entity.FriendZoneEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.friend_model.FriendModelGet;
import com.itacademy.model.friend_model.FriendZoneModelGet;
import com.itacademy.model.users_models.UserModelGet;
import com.itacademy.service.FriendService;
import com.itacademy.service.FriendZoneService;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friend")
public class FriendController {
    @Autowired
    private FriendZoneService zoneService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private UsersService usersService;

    @GetMapping("/getAllFriendZone")
    public List<FriendZoneModelGet> getAllFriendZone() {
        return zoneService.convertFriendZoneList(zoneService.getAll());
    }

    @PostMapping("/getAllFriendZoneUser")
    public FriendZoneModelGet getAllFriendZoneUser(@RequestBody UserModelGet getUser) {
        return zoneService.convertFriendZone(
                zoneService.getFriendZoneByUser(
                        usersService.convertModelToEntity(getUser)));
    }

    @GetMapping("/getAllFriends")
    public List<FriendModelGet> getAllFriend() {
        return friendService.convertEntityToModelList(friendService.getAll());
    }

    @PostMapping("/newFriend")
    public UserModelGet newFriend(@RequestBody UserModelGet get) {

        return usersService.convertUserEntityToUserModel(friendService.newFriend(get));
    }

    @DeleteMapping("/deleteFriend")
    public UserModelGet deleteFriend(@RequestBody UserModelGet get){

        return usersService.convertUserEntityToUserModel(
                zoneService.deleteFriend(
                        usersService.convertModelToEntity(get)));
    }
}
