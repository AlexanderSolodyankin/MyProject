package com.itacademy.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class MenuController {
    @GetMapping("/")
    public String menu(Model model){
        model.addAttribute("menu", "Главное меню Проверка");
        return "menu";
    }


}
