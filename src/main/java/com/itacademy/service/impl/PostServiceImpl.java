package com.itacademy.service.impl;

import com.itacademy.entity.PostUsersEntity;
import com.itacademy.entity.UserEntity;
import com.itacademy.model.post_model.PostModelGet;
import com.itacademy.repository.PostRepository;
import com.itacademy.service.PostCommentaryService;
import com.itacademy.service.PostService;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private PostCommentaryService postCommentaryService;

    @Override
    public List<PostUsersEntity> getAll() {
        return postRepository.findAll();
    }

    @Override
    public List<PostUsersEntity> getPostUserList(UserEntity userEntity) {
        return postRepository.findByUserEntity(userEntity).orElse(null);
    }

    @Override
    public PostUsersEntity newPost(PostUsersEntity postUsersEntity) {
        postUsersEntity.setUserEntity(usersService.getCurrentUser());
        return postRepository.save(postUsersEntity);
    }

    @Override
    public PostUsersEntity getByValues(String postValues) {
        return postRepository.findByPostValue(postValues).orElseThrow(() ->
                new IllegalArgumentException("Такого поста не существует"));
    }

    @Override
    public PostUsersEntity deletePost(Long id) {
        PostUsersEntity postUsersEntity = postRepository.getById(id);
        if (!postUsersEntity.getUserEntity().equals(usersService.getCurrentUser())) {
            throw new IllegalArgumentException("Пост может удолить только тот кто его создал");
        }
        postRepository.delete(postUsersEntity);
        return postUsersEntity;
    }

    @Override
    public PostUsersEntity getById(Long id) {
        return postRepository.getById(id);
    }

    @Override
    public PostUsersEntity updatePost(PostUsersEntity postUsersEntity) {
        postUsersEntity.setUserEntity(usersService.getCurrentUser());
        return postRepository.save(postUsersEntity);
    }

    @Override
    public PostModelGet convertEntityToModel(PostUsersEntity postUsersEntity) {
        PostModelGet postModelGet = new PostModelGet();
        postModelGet.setId(postUsersEntity.getId());
        postModelGet.setPostValue(postUsersEntity.getPostValue());
        postModelGet.setCreateData(postUsersEntity.getCreateData());
        postModelGet.setPostCommentaryModelGetList(
                postCommentaryService.converEntityToModelList(
                        postCommentaryService.getAllPostUnit(postUsersEntity)));
        return postModelGet;
    }

    @Override
    public List<PostModelGet> convertEntityToModelList(List<PostUsersEntity> postUsersEntityList) {
        List<PostModelGet> postModelGetList = new ArrayList<>();
        for (PostUsersEntity postUsersEntity : postUsersEntityList) {
            postModelGetList.add(convertEntityToModel(postUsersEntity));
        }
        return postModelGetList;
    }
}
