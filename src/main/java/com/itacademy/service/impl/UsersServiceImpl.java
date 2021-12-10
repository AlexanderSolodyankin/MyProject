package com.itacademy.service.impl;

import com.itacademy.entity.UserEntity;
import com.itacademy.entity.UserRole;
import com.itacademy.model.UserAuthModel;
import com.itacademy.repository.RoleRepository;
import com.itacademy.repository.UsersRepository;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    @Override
    public UserEntity newUser(UserEntity user) {
        String encoderPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoderPassword);

        user = usersRepository.save(user); // тут должна пройти шифровка пароля

        UserRole userRole = new UserRole();
        userRole.setRoleName("ROLE_USER");
        userRole.setUserEntity(user);
        roleRepository.save(userRole);
        return  user;
    }

    @Override
    public UserEntity setAdmin(UserEntity user) {
        String encoderPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoderPassword);

        user = usersRepository.save(user);

        UserRole userRole = new UserRole();
        userRole.setRoleName("ROLE_ADMIN");
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
//        UserEntity updateUser = getAuthorizedToken(userAuthModel);
//        if(updateUser == null){
//            return null;
//        }
//        updateUser.setPassword(newPassword);
//        usersRepository.save(updateUser);
//        return updateUser;
        return null;
    }

    @Override
    public String getAuthorizedToken(UserAuthModel userAuthModel) throws IllegalAccessException {
        UserEntity userEntity = usersRepository.findByLogin(userAuthModel.getLogin()).orElseThrow(
                () -> new IllegalArgumentException("Неверный логин или пароль.")
        );
        boolean isPasswordMatches = passwordEncoder.matches(userAuthModel.getPassword(), userEntity.getPassword());
        if(!isPasswordMatches){
            throw new IllegalAccessException("Неверный логин или пароль.");
        }
        String userNamePasswordPair = userAuthModel.getLogin() + ": " + userAuthModel.getPassword();
        return "Basic " + new String(Base64.getEncoder().encode(userNamePasswordPair.getBytes()));
    }

    @Override
    public UserEntity deleteUser(UserAuthModel userAuthModel) throws IllegalAccessException {
//        UserEntity userEntity = getAuthorizedToken(userAuthModel);
//        UserRole userRoleDelete = roleRepository.findByUserEntity(userEntity).orElse(null);
//        if(userRoleDelete == null) throw new IllegalAccessException("Такой роли не существует");
//        roleRepository.delete(userRoleDelete);
//        usersRepository.delete(userEntity);
//        return userEntity;
        return null;
    }

    @Override
    public UserEntity getCurrentUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUser(userName);
    }
}
