package com.itacademy.model;

import lombok.*;

@Data
public class MailModel {
    private String toEmail;
    private String subject;
    private String text;
}
