package com.itacademy.game.controller;

import com.itacademy.game.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class MenuController {
    @Autowired
    private UsersService usersService;
    @GetMapping("/")
    public String menu(Model model){
        model.addAttribute("menu", "Главное меню ");
        model.addAttribute("allUsers", usersService.getAllUsers().size() +
                " Количество зарегестиррованых пользователей");
        return "menu";
    }


}
