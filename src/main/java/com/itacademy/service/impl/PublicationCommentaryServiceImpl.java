package com.itacademy.service.impl;

import com.itacademy.entity.PublicationCommentaryEntity;
import com.itacademy.entity.PublicationUsersEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.post_model.PublicationCommentaryModelGet;
import com.itacademy.model.post_model.PublicationModelGet;
import com.itacademy.model.post_model.PublicationModelPost;
import com.itacademy.repository.PublicationCommRepository;
import com.itacademy.service.PublicationCommentaryService;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PublicationCommentaryServiceImpl implements PublicationCommentaryService {
    @Autowired
    private PublicationCommRepository publicationCommRepository;
    @Autowired
    private UsersService usersService;

    @Override
    public List<PublicationCommentaryEntity> getAll() {
        return publicationCommRepository.findAll();
    }

    @Override
    public List<PublicationCommentaryEntity> getAllPostUnit(PublicationUsersEntity publicationUsersEntity) {
        return publicationCommRepository.findByPostUsersEntity(publicationUsersEntity).orElse(null);
    }

    @Override
    public PublicationCommentaryEntity newCommentary(PublicationModelPost post, Long id) {
        PublicationCommentaryEntity entity = convertModelToEntity(post);
        return publicationCommRepository.save(entity);
    }

    @Override
    public PublicationCommentaryEntity getCommentUsers(UserEntity userEntity) {
        return null;
    }

    @Override
    public PublicationCommentaryEntity deleteComment(PublicationCommentaryEntity publicationCommentaryEntity) {
        publicationCommRepository.delete(publicationCommentaryEntity);
        return publicationCommentaryEntity;
    }

    @Override
    public PublicationCommentaryEntity updateCommentary(PublicationModelGet get) {
        PublicationCommentaryEntity entity = getById(get.getId());
        entity.setValues(get.getPostValue());
        return publicationCommRepository.save(entity);
    }

    @Override
    public PublicationCommentaryEntity getById(Long id) {
        return publicationCommRepository.getById(id);
    }

    @Override
    public UserEntity getUsersByPost(PublicationCommentaryEntity publicationCommentaryEntity) {
        return publicationCommentaryEntity.getUserEntity();
    }

    @Override
    public PublicationCommentaryModelGet convertEntityToModel(PublicationCommentaryEntity publicationCommentaryEntity) {
        PublicationCommentaryModelGet publicationCommentaryModelGet = new PublicationCommentaryModelGet();
        publicationCommentaryModelGet.setId(publicationCommentaryModelGet.getId());
        publicationCommentaryModelGet.setUserModelGet(usersService.convertUserEntityToUserModel(publicationCommentaryEntity.getUserEntity()));
        publicationCommentaryModelGet.setValues(publicationCommentaryEntity.getValues());
        publicationCommentaryModelGet.setCreateData(publicationCommentaryEntity.getCreateData());
        return publicationCommentaryModelGet;
    }

    @Override
    public List<PublicationCommentaryModelGet> converEntityToModelList(
            List<PublicationCommentaryEntity> publicationCommentaryEntityList) {
        List<PublicationCommentaryModelGet> publicationCommentaryModelGetList = new ArrayList<>();
        for (PublicationCommentaryEntity publicationCommentaryEntity : publicationCommentaryEntityList) {
            publicationCommentaryModelGetList.add(convertEntityToModel(publicationCommentaryEntity));
        }
        return publicationCommentaryModelGetList;
    }

    @Override
    public PublicationCommentaryEntity convertModelToEntity(PublicationModelPost post) {
        PublicationCommentaryEntity entity = new PublicationCommentaryEntity();
        entity.setUserEntity(usersService.getCurrentUser());
        entity.setValues(post.getPostValue());
        return entity;
    }
}
