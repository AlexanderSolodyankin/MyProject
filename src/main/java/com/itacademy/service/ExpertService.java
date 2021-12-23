package com.itacademy.service;

import com.itacademy.entity.ExpertEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.expert_model.ExpertModelGet;
import com.itacademy.model.expert_model.ExpertModelPost;

import java.util.List;

public interface ExpertService {
    List<ExpertEntity> getAll();

    ExpertEntity saveExpert(ExpertModelPost expertModelPost);

    List<ExpertEntity> getExpert(UserEntity userEntity);

    ExpertEntity getExpert(Long id);

    ExpertEntity delete(Long id);

    ExpertModelGet convertEntityToModel(ExpertEntity expertEntity);

    List<ExpertModelGet> convertEntityToModelList(List<ExpertEntity> expertEntityList);

    ExpertEntity convertModelToEntity(ExpertModelPost expertModelPost);
}
