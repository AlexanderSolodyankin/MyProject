package com.itacademy.controller;

import com.itacademy.entity.ExpertEntity;
import com.itacademy.model.UserAuthModel;
import com.itacademy.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expert")
public class ExpertController {
    @Autowired
    private ExpertService expertService;

    @GetMapping("/getAll")
    public List<ExpertEntity> getAll(){
        return expertService.getAll();
    }

    @PostMapping("/saveExpert")
    public ExpertEntity saveExpert(@RequestBody ExpertEntity expertEntity){
        return expertService.saveExpert(expertEntity);
    }

    @PostMapping("/getExpert")
    public ExpertEntity getExpert(@RequestBody UserAuthModel userAuthModel){
        return expertService.getExpert(userAuthModel);
    }
    @GetMapping("/getExpert/{id}")
    public ExpertEntity getExpert(@PathVariable Long id){
        return expertService.getExpert(id);
    }

    @DeleteMapping("/deleteExpert")
    public ExpertEntity deleteExpert(@RequestBody UserAuthModel userAuthModel){
        return expertService.delete(userAuthModel);
    }
}
