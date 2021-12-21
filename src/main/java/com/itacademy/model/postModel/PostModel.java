package com.itacademy.model.postModel;


import com.itacademy.model.usersModels.UserModelGet;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
public class PostModel {
    private Long id;
    private LocalDateTime createData;
    private String postValue;
    private UserModelGet userModelGet;
    private List<PostCommentaryModel> postCommentaryModelList;


}
