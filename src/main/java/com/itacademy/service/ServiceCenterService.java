package com.itacademy.service;

import com.itacademy.entity.ServiceCenterEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.service_center_model.GetServiceCenterModel;
import com.itacademy.model.service_center_model.PostServiceCenterModel;

import java.util.List;

public interface ServiceCenterService {
    List<ServiceCenterEntity> getAllCerviceCenter();

    ServiceCenterEntity save(PostServiceCenterModel postServiceCenterModel);

    ServiceCenterEntity delete(Long Id);

    List<ServiceCenterEntity> getServiceCenter(UserEntity userEntity);

    ServiceCenterEntity getServiceCenter(Long id);

    GetServiceCenterModel convertServiceEntityToServiceModel(ServiceCenterEntity serviceCenterEntity);

    List<GetServiceCenterModel> convertServiceEntityToServiceModelList(List<ServiceCenterEntity> serviceCenterEntityList);

    ServiceCenterEntity convertModelToEntity(PostServiceCenterModel postServiceCenterModel);
}
