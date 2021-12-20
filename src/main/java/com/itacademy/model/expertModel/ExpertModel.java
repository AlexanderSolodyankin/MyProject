package com.itacademy.model.expertModel;

import com.itacademy.model.usersModels.UserModel;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ExpertModel {
    private Long id;
    private  String name;
    private String expertInfo;
    private UserModel userModel;
}