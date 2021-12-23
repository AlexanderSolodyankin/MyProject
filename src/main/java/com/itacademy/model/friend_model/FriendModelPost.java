package com.itacademy.model.friend_model;

import com.itacademy.model.users_models.UserModelGet;
import lombok.Data;

import java.util.List;

@Data
public class FriendModelPost {
    private UserModelGet user;
    private List<UserModelGet> friends;
}
