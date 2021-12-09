package com.itacademy.service;

import com.itacademy.entity.ServiceCenterEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.UserAuthModel;

import java.util.List;

public interface ServiceCenterService {
    List<ServiceCenterEntity> getAllCerviceCenter();
    ServiceCenterEntity save(ServiceCenterEntity serviceCenterEntity);
    ServiceCenterEntity delete(UserAuthModel userAuthModel);
    ServiceCenterEntity getServiceCenter(UserAuthModel userAuthModel);
    ServiceCenterEntity getServiceCenter(Long  id);
}
