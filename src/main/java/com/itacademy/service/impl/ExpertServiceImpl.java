package com.itacademy.service.impl;

import com.itacademy.entity.ExpertEntity;
import com.itacademy.model.UserAuthModel;
import com.itacademy.repository.ExpertRepository;
import com.itacademy.service.ExpertService;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        expertEntity.setUserEntity(usersService.getByUser(expertEntity.getUserEntity().getLogin()));
        return expertRepository.save(expertEntity);
    }

    @Override
    public ExpertEntity getExpert(UserAuthModel userAuthModel) {
//        return  expertRepository.findByUserEntity(
//                usersService.getAuthorizedToken(userAuthModel)
//        ).orElse(null);
        return null;

    }
    @Override
    public ExpertEntity getExpert(Long id) {
        return  expertRepository.findById(id).orElse(null);
    }

    @Override
    public ExpertEntity delete(UserAuthModel userAuthModel) {
        ExpertEntity expertEntityDelete = getExpert(userAuthModel);
        expertRepository.delete(expertEntityDelete);
        return expertEntityDelete;
    }
}
