package com.itacademy.model.usersModels;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserInfoModel {
    private Long id;
    private String name;
    private String serName;
    private String patronymic;
    private String phone;
    private String country;
    private String city;
    private String car;
    private boolean gender;
    private UserModel userModel;
}
