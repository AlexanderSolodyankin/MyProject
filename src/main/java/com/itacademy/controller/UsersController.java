package com.itacademy.controller;

import com.itacademy.entity.UserEntity;
import com.itacademy.model.UserAuthModel;
import com.itacademy.service.UserInfoService;
import com.itacademy.service.impl.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersServiceImpl usersService;
    @Autowired
    private UserInfoService userInfoService;



    @GetMapping("/getAll")
    public List<UserEntity> userMenu() {
        return usersService.getAllUsers();
    }

    @PostMapping("/registration")
    public UserEntity newUser(@RequestBody UserEntity userEntity) throws IllegalAccessException {
      if(usersService.getByUser(userEntity.getLogin()) != null){
        throw  new IllegalAccessException("Такой пользователь уже есть");
      }else
          return usersService.newUser(userEntity);
    }

    @PostMapping("/sing-in")
    public ResponseEntity<String> sing(@RequestBody UserAuthModel userAuthModel){
        return ResponseEntity.ok(usersService.getAuthorizedToken(userAuthModel));
    }

    @GetMapping("/get-current")
    public UserEntity getCurrent(){
        System.out.println("Зашол в контроллер ГетКАРЕНТ");
        return usersService.getCurrentUser();
    }



    @PostMapping("/update")
    public UserEntity setUpdateUser(@RequestBody UserAuthModel userAuthModel, @RequestParam String newPassword) {
        return usersService.updatePassword(userAuthModel, newPassword);
    }

    @DeleteMapping("/deleteUser")
    public UserEntity deleteUser(@RequestBody UserAuthModel userAuthModel) throws IllegalAccessException {
        return usersService.deleteUser(userAuthModel);
    }





}
