package com.itacademy.service.impl;

import com.itacademy.entity.ServiceCenterEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.serviceCenterModel.ServiceCenterModel;
import com.itacademy.repository.ServiceCenterReposit;
import com.itacademy.service.ServiceCenterService;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        serviceCenterEntity.setUserEntity(usersService.getCurrentUser());
        return serviceCenterReposit.save(serviceCenterEntity);
    }

    @Override
    public ServiceCenterEntity delete(UserEntity userEntity) {
       ServiceCenterEntity serviceCenterEntity =  getServiceCenter(userEntity);
       serviceCenterReposit.delete(serviceCenterEntity);
       return serviceCenterEntity;

    }

    @Override
    public ServiceCenterEntity getServiceCenter(UserEntity userEntity) {
        return serviceCenterReposit.findByUserEntity(userEntity).orElseThrow(
                () -> new IllegalArgumentException(" Сервис-Центра закрепленного по данному пользователю отсутствует! ")
        );

    }

    @Override
    public ServiceCenterEntity getServiceCenter(Long id) {
        return serviceCenterReposit.findById(id).orElseThrow(
                () -> new IllegalArgumentException(" Сервис-Центра под таким ID номером не существует! ")
                );
    }

    @Override
    public ServiceCenterModel convertServiceEntityToServiceModel(ServiceCenterEntity serviceCenterEntity) {
        ServiceCenterModel serviceCenterModel = new ServiceCenterModel();
        serviceCenterModel.setId(serviceCenterEntity.getId());
        serviceCenterModel.setName(serviceCenterEntity.getName());
        serviceCenterModel.setAddress(serviceCenterEntity.getAddress());
        serviceCenterModel.setPhone(serviceCenterEntity.getPhone());
        serviceCenterModel.setUserModel(
                usersService.convertUserEntityToUserModel(serviceCenterEntity.getUserEntity()));
        return serviceCenterModel;
    }

    @Override
    public List<ServiceCenterModel> convertServiceEntityToServiceModelList(
            List<ServiceCenterEntity> serviceCenterEntityList) {
        List<ServiceCenterModel> serviceCenterModelList = new ArrayList<>();
        for(ServiceCenterEntity serviceCenterEntity : serviceCenterEntityList){
            serviceCenterModelList.add(convertServiceEntityToServiceModel(serviceCenterEntity));
        }
        return serviceCenterModelList;
    }
}
