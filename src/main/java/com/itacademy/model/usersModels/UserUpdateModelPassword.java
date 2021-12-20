package com.itacademy.model.usersModels;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserUpdateModelPassword {
    private String OldPassword;
    private String newPassword;
}
