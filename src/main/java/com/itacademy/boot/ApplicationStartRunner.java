package com.itacademy.boot;


import com.itacademy.entity.*;
import com.itacademy.repository.*;
import com.itacademy.service.ExpertService;
import com.itacademy.service.ServiceCenterService;
import com.itacademy.service.UserInfoService;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartRunner implements CommandLineRunner {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserInfoRepository userInfoService;
    @Autowired
    private ServiceCenterReposit serviceCenterService;
    @Autowired
    private ExpertRepository expertService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {

        if (usersService.getByUser("admin") == null) {
            UserEntity admin = new UserEntity();
            admin.setLogin("admin");
            admin.setPassword( passwordEncoder.encode("admin"));
            admin.setIsActive(1L);
            admin.setEmail("Admin.Mail.Ru");
            admin = usersRepository.save(admin);

            UserRole userRole = new UserRole();
            userRole.setRoleName("ROLE_ADMIN");
            userRole.setUserEntity(admin);
            roleRepository.save(userRole);

        }



    }

    public void setDataBase(int count) {
        for (int i = 0; i < count; i++) {
            UserEntity userEntity = new UserEntity();
            userEntity.setPassword( passwordEncoder.encode("qwerty"));
            userEntity.setEmail("@mail" + i);
            userEntity.setLogin("User" + i);
            userEntity.setIsActive(1L);
            userEntity = usersRepository.save(userEntity);

            UserRole userRole = new UserRole();
            userRole.setRoleName("ROLE_USER");
            userRole.setUserEntity(userEntity);
            roleRepository.save(userRole);

            UserInfoEntity userInfoEntity = new UserInfoEntity();
            userInfoEntity.setUserEntity(userEntity);
            userInfoEntity.setName("Name " + i);
            userInfoEntity.setSerName("SerName " + i);
            userInfoEntity.setPatronymic("Patronymic " + i);
            userInfoEntity.setPhone("+996559");
            userInfoEntity.setCountry("Kygiz " + i);
            userInfoEntity.setCity("Bish " + i);
            userInfoEntity.setCar("Car " + i);
            if (i % 2 == 0) {
                userInfoEntity.setGender(true);
            } else userInfoEntity.setGender(false);
            userInfoService.save(userInfoEntity);

            ServiceCenterEntity serviceCenterEntity = new ServiceCenterEntity();
            serviceCenterEntity.setName("Service " + i);
            serviceCenterEntity.setAddress("Address + " + i);
            serviceCenterEntity.setPhone("+996559");
            serviceCenterEntity.setUserEntity(userEntity);
            serviceCenterService.save(serviceCenterEntity);

            ExpertEntity expertEntity = new ExpertEntity();
            expertEntity.setName("Expert " + i);
            expertEntity.setExpertInfo("Expert Information " + i);
            expertEntity.setUserEntity(userEntity);
            expertService.save(expertEntity);


        }
    }
}
