package com.itacademy.controller;

import com.itacademy.entity.ExpertEntity;
import com.itacademy.model.expertModel.ExpertModel;
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
    public List<ExpertModel> getAll(){
        return expertService.convertEntityToModelList( expertService.getAll());
    }

    @PostMapping("/saveExpert")
    public ExpertModel saveExpert(@RequestBody ExpertEntity expertEntity){
        return expertService.convertEntityToModel(expertService.saveExpert(expertEntity));
    }

    @GetMapping("/getExpert")
    public ExpertModel getExpert(){
        return expertService.convertEntityToModel(expertService.getExpert(usersService.getCurrentUser()));
    }
    @GetMapping("/getExpert/{id}")
    public ExpertModel getExpert(@PathVariable Long id){
        return expertService.convertEntityToModel(expertService.getExpert(id));
    }

    @DeleteMapping("/deleteExpert")
    public ExpertModel deleteExpert(){
        return expertService.convertEntityToModel(expertService.delete(usersService.getCurrentUser()));
    }
}
