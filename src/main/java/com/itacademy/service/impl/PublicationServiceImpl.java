package com.itacademy.service.impl;

import com.itacademy.entity.PublicationUsersEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.post_model.PublicationModelGet;
import com.itacademy.model.post_model.PublicationModelPost;
import com.itacademy.repository.PublicationRepository;
import com.itacademy.service.PublicationCommentaryService;
import com.itacademy.service.PublicationService;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublicationServiceImpl implements PublicationService {
    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private PublicationCommentaryService publicationCommentaryService;

    @Override
    public List<PublicationUsersEntity> getAll() {
        return publicationRepository.findAll();
    }

    @Override
    public List<PublicationUsersEntity> getPostUserList(UserEntity userEntity) {
        return publicationRepository.findByUserEntity(userEntity).orElse(null);
    }

    @Override
    public PublicationUsersEntity newPost(PublicationModelPost post) {
        return publicationRepository.save(convertModelToEntity(post));
    }

    @Override
    public PublicationUsersEntity getByValues(String postValues) {
        return publicationRepository.findByPostValue(postValues).orElseThrow(() ->
                new IllegalArgumentException("Такого поста не существует"));
    }

    @Override
    public PublicationUsersEntity deletePost(Long id) {
        PublicationUsersEntity publicationUsersEntity = publicationRepository.getById(id);
        if (!publicationUsersEntity.getUserEntity().equals(usersService.getCurrentUser())) {
            throw new IllegalArgumentException("Нельзя удолять чужую публикацию");
        }
        publicationRepository.delete(publicationUsersEntity);
        return publicationUsersEntity;
    }

    @Override
    public PublicationUsersEntity getById(Long id) {
        return publicationRepository.getById(id);
    }

    @Override
    public PublicationUsersEntity updatePost(PublicationModelGet get) {
        PublicationUsersEntity entity = getById(get.getId());
        entity.setPostValue(get.getPostValue());
        return publicationRepository.save(entity);
    }

    @Override
    public PublicationModelGet convertEntityToModel(PublicationUsersEntity publicationUsersEntity) {
        PublicationModelGet publicationModelGet = new PublicationModelGet();
        publicationModelGet.setId(publicationUsersEntity.getId());
        publicationModelGet.setPostValue(publicationUsersEntity.getPostValue());
        publicationModelGet.setCreateData(publicationUsersEntity.getCreateData());
        publicationModelGet.setPublicationCommentaryModelGetList(
                publicationCommentaryService.converEntityToModelList(
                        publicationCommentaryService.getAllPostUnit(publicationUsersEntity)));
        return publicationModelGet;
    }

    @Override
    public List<PublicationModelGet> convertEntityToModelList(List<PublicationUsersEntity> publicationUsersEntityList) {
        List<PublicationModelGet> publicationModelGetList = new ArrayList<>();
        for (PublicationUsersEntity publicationUsersEntity : publicationUsersEntityList) {
            publicationModelGetList.add(convertEntityToModel(publicationUsersEntity));
        }
        return publicationModelGetList;
    }

    @Override
    public PublicationUsersEntity convertModelToEntity(PublicationModelPost post) {
        PublicationUsersEntity publicationUsersEntity = new PublicationUsersEntity();
        publicationUsersEntity.setUserEntity(usersService.getCurrentUser());
        publicationUsersEntity.setPostValue(post.getPostValue());
        return publicationUsersEntity;
    }
}
