package com.itacademy.model.friend_model;

import com.itacademy.model.users_models.UserModelGet;
import lombok.Data;

@Data
public class FriendModelGet {
    private Long id;
    private UserModelGet userGet;
}
