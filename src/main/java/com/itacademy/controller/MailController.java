package com.itacademy.controller;

import com.itacademy.model.MailModel;
import com.itacademy.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping
    public Boolean send(@RequestBody MailModel mailModel){
        return mailService.send(mailModel.getToEmail(), mailModel.getSubject(), mailModel.getText());
    }
}
