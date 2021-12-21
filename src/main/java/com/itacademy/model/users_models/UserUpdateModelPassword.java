package com.itacademy.model.users_models;

import lombok.*;

@Data
public class UserUpdateModelPassword {
    private String OldPassword;
    private String newPassword;
}
