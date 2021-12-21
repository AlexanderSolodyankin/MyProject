package com.itacademy.service.impl;

import com.itacademy.entity.UserEntity;
import com.itacademy.entity.UserRole;
import com.itacademy.model.usersModels.UserAuthModel;
import com.itacademy.model.usersModels.UserModel;
import com.itacademy.model.usersModels.UserUpdateModelPassword;
import com.itacademy.repository.RoleRepository;
import com.itacademy.repository.UsersRepository;
import com.itacademy.service.MailService;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailService mailService;


    @Override
    public UserEntity newUser(UserEntity user) {
        UserEntity userDb = usersRepository.findByLogin(user.getLogin()).orElse(null);
        if (userDb != null || user.getId() != null) {
            throw new IllegalArgumentException("Такой пользователь существует!!");
        }
        String activationCode = user.getLogin() + ":" + user.getPassword();
        activationCode = new String(Base64.getEncoder().encode(activationCode.getBytes()));

        String encoderPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoderPassword);
        user.setIsActive(0L);

        user.setActivationCode(activationCode);
        user = usersRepository.save(user);


        UserRole userRole = new UserRole();
        userRole.setRoleName("ROLE_USER");
        userRole.setUserEntity(user);
        roleRepository.save(userRole);
        String messege = "https://driverroom.herokuapp.com/users/activation/" + activationCode;
        mailService.send(user.getEmail(), user.getLogin(), messege);
        return user;
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
        return usersRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(" Пользователя под таким ID номером не существует! ")
        );
    }

    @Override
    public UserEntity updatePassword(UserUpdateModelPassword userNewPassword) throws IllegalAccessException {
        UserEntity user = getCurrentUser();
        boolean isPasswordMatches = passwordEncoder.matches(userNewPassword.getOldPassword(), user.getPassword());
        if (!isPasswordMatches) {
            throw new IllegalAccessException("Неверный пароль.");
        }
        String newPassword = passwordEncoder.encode(userNewPassword.getNewPassword());
        user.setPassword(newPassword);
        return usersRepository.save(user);
    }

    @Override
    public String getAuthorizedToken(UserAuthModel userAuthModel) throws IllegalAccessException {
        UserEntity userEntity = usersRepository.findByLogin(userAuthModel.getLogin()).orElseThrow(
                () -> new IllegalArgumentException("Неверный логин или пароль."));

        boolean isPasswordMatches = passwordEncoder.matches(userAuthModel.getPassword(), userEntity.getPassword());
        if (!isPasswordMatches) {
            throw new IllegalAccessException("Неверный логин или пароль.");
        }
        String userNamePasswordPair = userAuthModel.getLogin() + ":" + userAuthModel.getPassword();
        return "Basic " + new String(Base64.getEncoder().encode(userNamePasswordPair.getBytes()));
    }

    @Override
    public UserEntity deleteUser(UserEntity userEntity) {
        UserRole userRoleDelete = roleRepository.findByUserEntity(userEntity).orElseThrow(
                () -> new IllegalArgumentException(" Такого пользователя не существует! ")
        );
        if (userRoleDelete == null) {
            try {
                throw new IllegalAccessException("Такой роли не существует");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        roleRepository.delete(userRoleDelete);
        usersRepository.delete(userEntity);
        return userEntity;

    }

    @Override
    public UserEntity getCurrentUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUser(userName);
    }

    @Override
    public UserEntity activationUser(String activation) {
        UserEntity userEntity = usersRepository.findByActivationCode(activation).orElseThrow(
                () -> new IllegalArgumentException("Проблемы с активацией акаунта"));

        if (userEntity.getActivationCode().equals(activation)) {
            userEntity.setIsActive(1L);
            userEntity.setActivationCode(null);
        }
        usersRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public UserModel convertUserEntityToUserModel(UserEntity userEntity) {
        UserModel userModel = new UserModel();
        userModel.setId(userEntity.getId());
        userModel.setLogin(userEntity.getLogin());
        userModel.setEmail(userEntity.getEmail());
        return userModel;
    }

    @Override
    public List<UserModel> convertUserEntityToUserModel(List<UserEntity> userEntity) {
        List<UserModel> userModelList = new ArrayList<>();
        for (UserEntity userEnty : userEntity) {
            userModelList.add(convertUserEntityToUserModel(userEnty));
        }
        return userModelList;
    }
}
