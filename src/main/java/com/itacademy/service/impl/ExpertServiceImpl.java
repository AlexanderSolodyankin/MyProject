package com.itacademy.service.impl;

import com.itacademy.entity.ExpertEntity;
import com.itacademy.entity.ServiceCenterEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.expertModel.ExpertModel;
import com.itacademy.model.serviceCenterModel.ServiceCenterModel;
import com.itacademy.repository.ExpertRepository;
import com.itacademy.service.ExpertService;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpertServiceImpl implements ExpertService {
    @Autowired
    private ExpertRepository expertRepository;
    @Autowired
    private UsersService usersService;

    @Override
    public List<ExpertEntity> getAll() {
        return expertRepository.findAll();
    }

    @Override
    public ExpertEntity saveExpert(ExpertEntity expertEntity) {
        System.out.println("перед токеном " +expertEntity);
        expertEntity.setUserEntity(usersService.getCurrentUser());
        return expertRepository.save(expertEntity);
    }

    @Override
    public ExpertEntity getExpert(UserEntity userEntity) {
        return expertRepository.findByUserEntity(userEntity).orElseThrow(
                () -> new IllegalArgumentException(" Эксперта закрепленного по данному пользователю не найдено! ")
        );

    }
    @Override
    public ExpertEntity getExpert(Long id) {
        return  expertRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(" Эксперта по данному ID номеру не существует! ")
        );
    }

    @Override
    public ExpertEntity delete(UserEntity userEntity) {
        ExpertEntity expertEntityDelete = getExpert(userEntity);
        expertRepository.delete(expertEntityDelete);
        return expertEntityDelete;
    }

    @Override
    public ExpertModel convertEntityToModel(ExpertEntity expertEntity) {
        ExpertModel expertModel = new ExpertModel();
        expertModel.setId(expertEntity.getId());
        expertModel.setName(expertEntity.getName());
        expertModel.setExpertInfo(expertEntity.getExpertInfo());
        expertModel.setUserModel(usersService.convertUserEntityToUserModel(expertEntity.getUserEntity()));
        return expertModel;
    }

    @Override
    public List<ExpertModel> convertEntityToModelList(List<ExpertEntity> expertEntityList) {
            List<ExpertModel> expertModelList = new ArrayList<>();
            for(ExpertEntity expertEntity : expertEntityList){
                expertModelList.add(convertEntityToModel(expertEntity));
            }
        return expertModelList;
    }
}
