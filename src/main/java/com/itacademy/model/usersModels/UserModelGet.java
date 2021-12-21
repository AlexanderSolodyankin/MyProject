package com.itacademy.model.usersModels;

import lombok.Data;
import lombok.ToString;

@Data
public class UserModelGet {
    private Long id;
    private String login;
    private String email;
}
