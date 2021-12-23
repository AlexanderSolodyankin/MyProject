package com.itacademy.model.post_model;

import com.itacademy.model.users_models.UserModelGet;
import lombok.Data;


@Data
public class PublicationModelPost {
    private String postValue;
    private UserModelGet userModelGet;
}
