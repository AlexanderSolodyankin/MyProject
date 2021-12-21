package com.itacademy.model.postModel;

import com.itacademy.entity.PostUsersEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.usersModels.UserModel;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class PostModel {
    private Long id;
    private LocalDateTime createData;
    private String postValue;
    private UserModel userModel;


}
