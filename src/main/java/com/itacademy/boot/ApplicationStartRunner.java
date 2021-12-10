package com.itacademy.boot;


import com.itacademy.entity.UserEntity;
import com.itacademy.repository.RoleRepository;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartRunner implements CommandLineRunner {

    @Autowired
    private UsersService usersService;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if(usersService.getByUser("admin") == null){
            UserEntity admin = new UserEntity();
            admin.setLogin("admin");
            admin.setPassword("admin");
            admin.setIsActive(1L);
            usersService.setAdmin(admin);
        }
    }
}
