package com.itacademy.model.postModel;


import com.itacademy.model.usersModels.UserModel;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class PostCommentaryModel {
    private Long id;
    private String values;
    private UserModel userModel;
    private LocalDateTime createData;
}
