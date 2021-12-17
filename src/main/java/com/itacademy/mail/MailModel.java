package com.itacademy.mail;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MailModel {
    private String toEmail;
    private String subject;
    private String text;
}
