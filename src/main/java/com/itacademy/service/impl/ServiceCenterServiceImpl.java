package com.itacademy.service.impl;

import com.itacademy.entity.ServiceCenterEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.service_center_model.GetServiceCenterModel;
import com.itacademy.model.service_center_model.PostServiceCenterModel;
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
    public ServiceCenterEntity save(PostServiceCenterModel postServiceCenterModel) {
        ServiceCenterEntity serviceCenterEntity = convertModelToEntity(postServiceCenterModel);
        serviceCenterEntity.setUserEntity(usersService.getCurrentUser());
        return serviceCenterReposit.save(serviceCenterEntity);
    }

    @Override
    public ServiceCenterEntity delete(UserEntity userEntity) {
        ServiceCenterEntity serviceCenterEntity = getServiceCenter(userEntity);
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
    public GetServiceCenterModel convertServiceEntityToServiceModel(ServiceCenterEntity serviceCenterEntity) {
        GetServiceCenterModel getServiceCenterModel = new GetServiceCenterModel();
        getServiceCenterModel.setId(serviceCenterEntity.getId());
        getServiceCenterModel.setName(serviceCenterEntity.getName());
        getServiceCenterModel.setAddress(serviceCenterEntity.getAddress());
        getServiceCenterModel.setPhone(serviceCenterEntity.getPhone());
        getServiceCenterModel.setUserModelGet(
                usersService.convertUserEntityToUserModel(serviceCenterEntity.getUserEntity()));
        return getServiceCenterModel;
    }

    @Override
    public List<GetServiceCenterModel> convertServiceEntityToServiceModelList(
            List<ServiceCenterEntity> serviceCenterEntityList) {
        List<GetServiceCenterModel> getServiceCenterModelList = new ArrayList<>();
        for (ServiceCenterEntity serviceCenterEntity : serviceCenterEntityList) {
            getServiceCenterModelList.add(convertServiceEntityToServiceModel(serviceCenterEntity));
        }
        return getServiceCenterModelList;
    }

    @Override
    public ServiceCenterEntity convertModelToEntity(PostServiceCenterModel postServiceCenterModel) {
        ServiceCenterEntity serviceCenterEntity = new ServiceCenterEntity();
        serviceCenterEntity.setName(postServiceCenterModel.getName());
        serviceCenterEntity.setAddress(postServiceCenterModel.getAddress());
        serviceCenterEntity.setPhone(postServiceCenterModel.getPhone());
        return serviceCenterEntity;
    }
}
