package com.itacademy.model.post_model;


import com.itacademy.model.users_models.UserModelGet;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class PostCommentaryModelGet {
    private Long id;
    private String values;
    private UserModelGet userModelGet;
    private LocalDateTime createData;
}
