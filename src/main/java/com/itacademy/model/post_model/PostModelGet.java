package com.itacademy.model.post_model;


import com.itacademy.model.users_models.UserModelGet;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
public class PostModelGet {
    private Long id;
    private LocalDateTime createData;
    private String postValue;
    private UserModelGet userModelGet;
    private List<PostCommentaryModelGet> postCommentaryModelGetList;
}
