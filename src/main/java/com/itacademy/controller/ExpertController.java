package com.itacademy.controller;

import com.itacademy.entity.ExpertEntity;
import com.itacademy.service.ExpertService;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expert")
public class ExpertController {
    @Autowired
    private ExpertService expertService;
    @Autowired
    private UsersService usersService;

    @GetMapping("/getAll")
    public List<ExpertEntity> getAll(){
        return expertService.getAll();
    }

    @PostMapping("/saveExpert")
    public ExpertEntity saveExpert(@RequestBody ExpertEntity expertEntity){
        return expertService.saveExpert(expertEntity);
    }

    @GetMapping("/getExpert")
    public ExpertEntity getExpert(){
        return expertService.getExpert(usersService.getCurrentUser());
    }
    @GetMapping("/getExpert/{id}")
    public ExpertEntity getExpert(@PathVariable Long id){
        return expertService.getExpert(id);
    }

    @DeleteMapping("/deleteExpert")
    public ExpertEntity deleteExpert(){
        return expertService.delete(usersService.getCurrentUser());
    }
}
