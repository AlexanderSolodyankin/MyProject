package com.itacademy.service.impl;

import com.itacademy.entity.UserEntity;
import com.itacademy.entity.UserRole;
import com.itacademy.model.UserAuthModel;
import com.itacademy.repository.RoleRepository;
import com.itacademy.repository.UsersRepository;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserEntity newUser(UserEntity user) {

        usersRepository.save(user); // тут должна пройти шифровка пароля

        UserRole userRole = new UserRole();
        userRole.setRoleName("ROLE_USER");
        userRole.setUserEntity(user);
        roleRepository.save(userRole);
        return  user;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public UserEntity getByUserLogin(String login) {
        return usersRepository.findByLogin(login).orElse(null);
    }

    @Override
    public UserEntity getAuthorizet(UserAuthModel userAuthModel) {
        UserEntity userEntity = getByUserLogin(userAuthModel.getLogin());
        if(!userEntity.getPassword().equals(userAuthModel.getPassword())){
            userEntity = null;
        }
        return userEntity;
    }

    @Override
    public UserEntity deleteUser(UserAuthModel userAuthModel) {
        UserEntity userEntity = getAuthorizet(userAuthModel);
        usersRepository.delete(userEntity);
        return userEntity;
    }
}
