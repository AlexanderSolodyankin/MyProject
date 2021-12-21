package com.itacademy.service;

import com.itacademy.entity.ServiceCenterEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.serviceCenterModel.ServiceCenterModel;

import java.util.List;

public interface ServiceCenterService {
    List<ServiceCenterEntity> getAllCerviceCenter();

    ServiceCenterEntity save(ServiceCenterEntity serviceCenterEntity);

    ServiceCenterEntity delete(UserEntity userEntity);

    ServiceCenterEntity getServiceCenter(UserEntity userEntity);

    ServiceCenterEntity getServiceCenter(Long id);

    ServiceCenterModel convertServiceEntityToServiceModel(ServiceCenterEntity serviceCenterEntity);

    List<ServiceCenterModel> convertServiceEntityToServiceModelList(List<ServiceCenterEntity> serviceCenterEntityList);
}
