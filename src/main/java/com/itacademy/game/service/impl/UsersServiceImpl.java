package com.itacademy.game.service.impl;

import com.itacademy.game.entity.UserEntity;
import com.itacademy.game.repository.UsersRepository;
import com.itacademy.game.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Override
    public UserEntity newUser(UserEntity user) {
            return usersRepository.save(user);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return usersRepository.findAll();
    }

//    @Override
//    public Users getByUserLogin(String login) {
//        return usersRepository.findByUserLogin(login).orElse(null);
//    }
}
