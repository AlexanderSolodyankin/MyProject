package com.itacademy.model.usersModels;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserModel {
    private Long id;
    private String login;
    private String email;
}
