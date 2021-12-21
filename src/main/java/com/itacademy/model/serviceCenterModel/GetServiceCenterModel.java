package com.itacademy.model.serviceCenterModel;

import com.itacademy.model.usersModels.UserModelGet;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GetServiceCenterModel {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private UserModelGet userModelGet;
}
