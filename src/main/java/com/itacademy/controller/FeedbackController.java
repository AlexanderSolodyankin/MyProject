package com.itacademy.controller;

import com.itacademy.config.MailSenderConfig;
import com.itacademy.model.Feedback;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private MailSenderConfig mailSenderConfig;

    public FeedbackController(MailSenderConfig mailSenderConfig){
        this.mailSenderConfig = mailSenderConfig;
    }

    @PostMapping
    public void sendFeedback(@RequestBody Feedback feedback, BindingResult bindingResult) throws IllegalAccessException {
        if(bindingResult.hasErrors()){
            throw  new IllegalAccessException("Письмо не полученно!!");
        }

        // создаем отправителя письма
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.mailSenderConfig.getHost());
        mailSender.setPort(this.mailSenderConfig.getPort());
        mailSender.setUsername(this.mailSenderConfig.getUsername());
        mailSender.setPassword(this.mailSenderConfig.getPassword());

        // Создаем экземпляр Письма
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(feedback.getEmail());
        mailMessage.setTo("89515014507solra@gmail.com");
        mailMessage.setSubject("new feedback from " + feedback.getName());
        mailMessage.setText(feedback.getFeedback());
        // Send mail
        mailSender.send(mailMessage);

    }
}
