package com.itacademy.service.impl;

import com.itacademy.entity.ExpertEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.expert_model.ExpertModelGet;
import com.itacademy.model.expert_model.ExpertModelPost;
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
    public ExpertEntity saveExpert(ExpertModelPost expertModelPost) {
        ExpertEntity expertEntity = convertModelToEntity(expertModelPost);
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
        return expertRepository.findById(id).orElseThrow(
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
    public ExpertModelGet convertEntityToModel(ExpertEntity expertEntity) {
        ExpertModelGet expertModelGet = new ExpertModelGet();
        expertModelGet.setId(expertEntity.getId());
        expertModelGet.setName(expertEntity.getName());
        expertModelGet.setExpertInfo(expertEntity.getExpertInfo());
        expertModelGet.setUserModelGet(usersService.convertUserEntityToUserModel(expertEntity.getUserEntity()));
        return expertModelGet;
    }

    @Override
    public List<ExpertModelGet> convertEntityToModelList(List<ExpertEntity> expertEntityList) {
        List<ExpertModelGet> expertModelGetList = new ArrayList<>();
        for (ExpertEntity expertEntity : expertEntityList) {
            expertModelGetList.add(convertEntityToModel(expertEntity));
        }
        return expertModelGetList;
    }

    @Override
    public ExpertEntity convertModelToEntity(ExpertModelPost expertModelPost) {
        ExpertEntity expertEntity = new ExpertEntity();
        expertEntity.setName(expertModelPost.getName());
        expertEntity.setExpertInfo(expertModelPost.getExpertInfo());
        return expertEntity;
    }
}
