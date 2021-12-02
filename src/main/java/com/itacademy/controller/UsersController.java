package com.itacademy.controller;

import com.itacademy.entity.Person;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.UserAuthModel;
import com.itacademy.service.PersonService;
import com.itacademy.service.impl.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UsersServiceImpl usersService;
    @Autowired
    private PersonService personService;

    @GetMapping("/getAll")
    public List<UserEntity> userMenu() {
        return usersService.getAllUsers();
    }

    @PostMapping("/registration")
    public UserEntity newUser(@RequestBody Person person) {
        // вошла информация персоны и производится регистрация и персоны
        // и Юзера с проверкой на наличие Юзера
        if(usersService.getByUserLogin(person.getUserEntity().getLogin()) != null){
//            throw new IllegalAccessException("Такой логин уже существует");
            System.err.println("Такой логин уже есть");
            // тут могла быть ваша реклама
        }
        person = personService.save(person);
        return person.getUserEntity();
    }


    @PostMapping("/log-in")
    public UserEntity getUser(@RequestBody UserAuthModel userAuthModel) {
        UserEntity userEntity = usersService.getAuthorizet(userAuthModel);
        System.out.println();
        return userEntity;
    }

    @PostMapping("/deleteUser")
    public UserEntity deleteUser(@RequestBody UserAuthModel userAuthModel) {
        return usersService.deleteUser(userAuthModel);
    }


}
