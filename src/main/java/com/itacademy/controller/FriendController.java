package com.itacademy.controller;

import com.itacademy.entity.FriendEntity;
import com.itacademy.entity.FriendZoneEntity;
import com.itacademy.model.users_models.UserModelGet;
import com.itacademy.service.FriendService;
import com.itacademy.service.FriendZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OneToMany;
import java.util.List;

@RestController
@RequestMapping("/api/friend")
public class FriendController {
    @Autowired
    private FriendZoneService zoneService;

    @Autowired
    private FriendService friendService;




    @GetMapping("/getAllFriendZone")
    public List<FriendZoneEntity> getAllFriendZone(){
        return zoneService.getAll();
    }

    @GetMapping("/getAllFriends")
    public List<FriendEntity> getAllFriend(){
        return friendService.getAll();
    }

    @PostMapping("/newFriend")
    public Boolean newFriend(@RequestBody UserModelGet get){
        return friendService.newFriend(get);
    }
}
