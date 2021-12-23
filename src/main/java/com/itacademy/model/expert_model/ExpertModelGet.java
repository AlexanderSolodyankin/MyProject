package com.itacademy.model.expert_model;

import com.itacademy.model.users_models.UserModelGet;
import lombok.Data;

@Data
public class ExpertModelGet {
    private Long id;
    private String name;
    private String expertInfo;
    private UserModelGet userModelGet;
}
