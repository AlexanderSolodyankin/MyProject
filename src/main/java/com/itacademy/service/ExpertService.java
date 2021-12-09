package com.itacademy.service;

import com.itacademy.entity.ExpertEntity;
import com.itacademy.model.UserAuthModel;

import java.util.List;

public interface ExpertService {
    List<ExpertEntity> getAll();
    ExpertEntity saveExpert(ExpertEntity expertEntity);
    ExpertEntity getExpert(UserAuthModel userAuthModel);
    ExpertEntity getExpert(Long id);
    ExpertEntity delete(UserAuthModel userAuthModel);
}
