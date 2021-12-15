package com.itacademy.service;

import com.itacademy.entity.ExpertEntity;
import com.itacademy.entity.UserEntity;

import java.util.List;

public interface ExpertService {
    List<ExpertEntity> getAll();
    ExpertEntity saveExpert(ExpertEntity expertEntity);
    ExpertEntity getExpert(UserEntity userEntity);
    ExpertEntity getExpert(Long id);
    ExpertEntity delete(UserEntity userEntity);
}
