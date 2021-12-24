package com.itacademy.model.friend_model;

import com.itacademy.model.users_models.UserModelGet;
import lombok.Data;

import java.util.List;

@Data
public class FriendZoneModelPost {
    private Long id;
    private UserModelGet user;
}
