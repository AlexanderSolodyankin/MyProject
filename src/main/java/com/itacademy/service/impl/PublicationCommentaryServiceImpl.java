package com.itacademy.service.impl;

import com.itacademy.entity.PublicationCommentaryEntity;
import com.itacademy.entity.PublicationUsersEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.post_model.PublicationCommentaryModelGet;
import com.itacademy.model.post_model.PublicationCommintaryPost;
import com.itacademy.model.post_model.PublicationModelGet;
import com.itacademy.repository.PublicationCommRepository;
import com.itacademy.service.PublicationCommentaryService;
import com.itacademy.service.PublicationService;
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
    @Autowired
    private PublicationService postService;

    @Override
    public List<PublicationCommentaryEntity> getAll() {
        return publicationCommRepository.findAll();
    }

    @Override
    public List<PublicationCommentaryEntity> getAllPostUnit(PublicationUsersEntity publicationUsersEntity) {
        return publicationCommRepository.findByPublicationUsersEntity(publicationUsersEntity).orElse(null);
    }

    @Override
    public PublicationCommentaryEntity newCommentary(PublicationCommintaryPost post, Long id) {
        PublicationCommentaryEntity entity = convertModelToEntity(post);
        entity.setPublicationUsersEntity(postService.getById(id));
        entity.setUserEntity(usersService.getCurrentUser());
        return publicationCommRepository.save(entity);
    }

    @Override
    public PublicationCommentaryEntity getCommentUsers(UserEntity userEntity) {
        return null;
    }

    @Override
    public PublicationCommentaryEntity deleteComment(PublicationCommentaryEntity entity) {

        if(!usersService.isAdmin(usersService.getCurrentUser())){
            if(!entity.getUserEntity().equals(usersService.getCurrentUser())){
                throw  new IllegalArgumentException("Нельзя удолять чужой коментарий!!!");
            }
        }
            publicationCommRepository.delete(entity);
        return entity;
    }

    @Override
    public PublicationCommentaryEntity updateCommentary(PublicationModelGet get) {

        PublicationCommentaryEntity entity = getById(get.getId());
        if(!usersService.isAdmin(usersService.getCurrentUser())) {
            if (!entity.getUserEntity().equals(usersService.getCurrentUser())) {
                throw new IllegalArgumentException("Нельзя менять чужой коментарий!!!");
            }
        }
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
    public PublicationCommentaryModelGet convertEntityToModel(PublicationCommentaryEntity entity) {
        PublicationCommentaryModelGet model = new PublicationCommentaryModelGet();
        model.setId(entity.getId());
        model.setUserModelGet(usersService.convertUserEntityToUserModel(entity.getUserEntity()));
        model.setValues(entity.getValues());
        model.setCreateData(entity.getCreateData());
        return model;
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
    public PublicationCommentaryEntity convertModelToEntity(PublicationCommintaryPost post) {
        PublicationCommentaryEntity entity = new PublicationCommentaryEntity();
        entity.setValues(post.getValues());
        return entity;
    }
    @Override
    public PublicationCommentaryEntity convertModelToEntity(PublicationCommentaryModelGet get) {
        PublicationCommentaryEntity entity = getById(get.getId());

        return entity;
    }
}
