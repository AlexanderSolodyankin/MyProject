package com.itacademy.game.controller.user;

import com.itacademy.game.entity.Users;
import com.itacademy.game.service.impl.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/user")
public class UsersController {
    @Autowired
    private UsersServiceImpl usersService;

    @GetMapping("user")
    public String userMenu(Model model){
        return "users";
    }

    @PostMapping("/user/newUser")
    public String newUser(@RequestParam String login, @RequestParam String password, Model model){
        Users  user = Users.builder().login(login).password(password).build();
        usersService.newUser(user);
        model.addAttribute("menu", user.getLogin() + " Успешно зарегистрирован!!");
        return "menu";
    }

//    @PostMapping("/user/newUser") //Тест на прием обьекта через Json
//    public String newUser(@RequestBody Users user, Model model){
//        usersService.newUser(user);
//        model.addAttribute("menu", user.getLogin() + " Успешно зарегистрирован!!");
//        return "menu";
//    }

}
