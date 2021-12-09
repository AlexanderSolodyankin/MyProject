package com.itacademy.service.impl;

import com.itacademy.entity.ServiceCenterEntity;
import com.itacademy.model.UserAuthModel;
import com.itacademy.repository.ServiceCenterReposit;
import com.itacademy.service.ServiceCenterService;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCenterServiceImpl implements ServiceCenterService {
    @Autowired
    private ServiceCenterReposit serviceCenterReposit;

    @Autowired
    private UsersService usersService;


    @Override
    public List<ServiceCenterEntity> getAllCerviceCenter() {
        return serviceCenterReposit.findAll();
    }

    @Override
    public ServiceCenterEntity save(ServiceCenterEntity serviceCenterEntity) {
        serviceCenterEntity.setUserEntity(usersService.getByUser(serviceCenterEntity.getUserEntity().getLogin()));
        return serviceCenterReposit.save(serviceCenterEntity);
    }

    @Override
    public ServiceCenterEntity delete(UserAuthModel userAuthModel) {
       ServiceCenterEntity serviceCenterEntity =  getServiceCenter(userAuthModel);
       serviceCenterReposit.delete(serviceCenterEntity);
       return serviceCenterEntity;

    }

    @Override
    public ServiceCenterEntity getServiceCenter(UserAuthModel userAuthModel) {
       ServiceCenterEntity serviceCenterEntity = serviceCenterReposit.findByUserEntity(
               usersService.getAuthorized(userAuthModel)).orElse(null);
        return serviceCenterEntity;
    }

    @Override
    public ServiceCenterEntity getServiceCenter(Long id) {
        return serviceCenterReposit.findById(id).orElse(null);
    }
}
