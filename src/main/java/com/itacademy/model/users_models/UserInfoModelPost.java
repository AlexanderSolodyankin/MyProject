package com.itacademy.model.users_models;

import lombok.Data;

@Data
public class UserInfoModelPost {
    private String name;
    private String serName;
    private String patronymic;
    private String phone;
    private String country;
    private String city;
    private String car;
    private boolean gender;
}
