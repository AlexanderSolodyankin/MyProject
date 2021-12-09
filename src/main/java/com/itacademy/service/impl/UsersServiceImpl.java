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

        user = usersRepository.save(user); // тут должна пройти шифровка пароля

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
    public UserEntity getByUser(String login) {
        return usersRepository.findByLogin(login).orElse(null);
    }


    @Override
    public UserEntity getByUser(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    @Override
    public UserEntity updatePassword(UserAuthModel userAuthModel, String newPassword) throws IllegalArgumentException {
        UserEntity updateUser = getAuthorized(userAuthModel);
        if(updateUser == null){
            return null;
        }
        updateUser.setPassword(newPassword);
        usersRepository.save(updateUser);
        return updateUser;
    }

    @Override
    public UserEntity getAuthorized(UserAuthModel userAuthModel) {
        UserEntity userEntity = getByUser(userAuthModel.getLogin());
        if(!userEntity.getPassword().equals(userAuthModel.getPassword())){
            userEntity = null;
        }
        return userEntity;
    }

    @Override
    public UserEntity deleteUser(UserAuthModel userAuthModel) throws IllegalAccessException {
        UserEntity userEntity = getAuthorized(userAuthModel);
        UserRole userRoleDelete = roleRepository.findByUserEntity(userEntity).orElse(null);
        if(userRoleDelete == null) throw new IllegalAccessException("Такой роли не существует");
        roleRepository.delete(userRoleDelete);
        usersRepository.delete(userEntity);
        return userEntity;
    }
}
