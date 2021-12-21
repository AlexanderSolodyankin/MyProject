package com.itacademy.model.postModel;


import com.itacademy.model.usersModels.UserModelGet;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class PostCommentaryModel {
    private Long id;
    private String values;
    private UserModelGet userModelGet;
    private LocalDateTime createData;
}
