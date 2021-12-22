package com.itacademy.service.impl;

import com.itacademy.entity.UserEntity;
import com.itacademy.entity.UserRole;
import com.itacademy.model.users_models.UserModelPost;
import com.itacademy.model.users_models.UserAuthModelPost;
import com.itacademy.model.users_models.UserModelGet;
import com.itacademy.model.users_models.UserUpdateModelPassword;
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
    public UserEntity newUser(UserModelPost userModelPost) {
        UserEntity userDb = usersRepository.findByLogin(userModelPost.getLogin()).orElse(null);
        if (userDb != null) {
            throw new IllegalArgumentException("Такой пользователь существует!!");
        }
        String activationCode = userModelPost.getLogin() + ":" + userModelPost.getPassword();
        activationCode = new String(Base64.getEncoder().encode(activationCode.getBytes()));

        String encoderPassword = passwordEncoder.encode(userModelPost.getPassword());
        userModelPost.setPassword(encoderPassword);

        UserEntity userEntity = convertModelToEntity(userModelPost);
        userEntity.setIsActive(0L);

        userEntity.setActivationCode(activationCode);
        userEntity = usersRepository.save(userEntity);


        UserRole userRole = new UserRole();
        userRole.setRoleName("ROLE_USER");
        userRole.setUserEntity(userEntity);
        roleRepository.save(userRole);

        String messege = "Куват принимай ссылку из моего кода https://driverroom.herokuapp.com/users/activation/" + activationCode;
        mailService.send(userEntity.getEmail(), userEntity.getLogin(), messege);
        return userEntity;
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
    public String getAuthorizedToken(UserAuthModelPost userAuthModelPost) throws IllegalAccessException {
        UserEntity userEntity = usersRepository.findByLogin(userAuthModelPost.getLogin()).orElseThrow(
                () -> new IllegalArgumentException("Неверный логин или пароль."));

        boolean isPasswordMatches = passwordEncoder.matches(userAuthModelPost.getPassword(), userEntity.getPassword());
        if (!isPasswordMatches) {
            throw new IllegalAccessException("Неверный логин или пароль.");
        }
        String userNamePasswordPair = userAuthModelPost.getLogin() + ":" + userAuthModelPost.getPassword();
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
    public String activationUser(String activation) {
        UserEntity userEntity = usersRepository.findByActivationCode(activation).orElseThrow(
                () -> new IllegalArgumentException("Проблемы с активацией акаунта"));

        if (userEntity.getActivationCode().equals(activation)) {
            userEntity.setIsActive(1L);
            userEntity.setActivationCode(null);
        }
        usersRepository.save(userEntity);
        return "Basic " + activation;
    }

    @Override
    public UserModelGet convertUserEntityToUserModel(UserEntity userEntity) {
        UserModelGet userModelGet = new UserModelGet();
        userModelGet.setId(userEntity.getId());
        userModelGet.setLogin(userEntity.getLogin());
        userModelGet.setEmail(userEntity.getEmail());
        return userModelGet;
    }

    @Override
    public List<UserModelGet> convertUserEntityToUserModel(List<UserEntity> userEntity) {
        List<UserModelGet> userModelGetList = new ArrayList<>();
        for (UserEntity userEnty : userEntity) {
            userModelGetList.add(convertUserEntityToUserModel(userEnty));
        }
        return userModelGetList;
    }

    @Override
    public UserEntity convertModelToEntity(UserModelPost userModelPost) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(userModelPost.getLogin());
        userEntity.setEmail(userModelPost.getEmail());
        userEntity.setPassword(userModelPost.getPassword());
        return userEntity;
    }


}
