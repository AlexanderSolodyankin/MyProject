package com.itacademy.model;

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
