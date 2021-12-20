package com.itacademy.model.serviceCenterModel;

import com.itacademy.model.usersModels.UserModel;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ServiceCenterModel {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private UserModel userModel;
}
