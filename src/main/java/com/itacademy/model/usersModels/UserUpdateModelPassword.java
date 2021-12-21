package com.itacademy.model.usersModels;

import lombok.*;

@Data
public class UserUpdateModelPassword {
    private String OldPassword;
    private String newPassword;
}
