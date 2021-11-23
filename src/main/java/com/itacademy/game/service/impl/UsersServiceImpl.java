package com.itacademy.game.service.impl;

import com.itacademy.game.entity.Users;
import com.itacademy.game.repository.UsersRepository;
import com.itacademy.game.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Override
    public Users newUser(Users user) {
            return usersRepository.save(user);
    }
}
