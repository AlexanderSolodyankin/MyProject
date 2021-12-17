package com.itacademy.mail;

public interface MailService {
    Boolean send(String toEmail, String subject, String text);
}
