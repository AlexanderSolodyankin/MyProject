package com.itacademy.model.service_center_model;

import com.itacademy.model.users_models.UserModelGet;
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
