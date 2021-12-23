package com.itacademy.controller;

import com.itacademy.model.expert_model.ExpertModelGet;
import com.itacademy.model.expert_model.ExpertModelPost;
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
    public List<ExpertModelGet> getAll() {
        return expertService.convertEntityToModelList(expertService.getAll());
    }

    @PostMapping("/saveExpert")
    public ExpertModelGet saveExpert(@RequestBody ExpertModelPost expertModelPost) {
        return expertService.convertEntityToModel(expertService.saveExpert(expertModelPost));
    }

    @GetMapping("/getExpert")
    public List<ExpertModelGet> getExpert() {
        return expertService.convertEntityToModelList(expertService.getExpert(usersService.getCurrentUser()));
    }

    @GetMapping("/getExpert/{id}")
    public ExpertModelGet getExpert(@PathVariable Long id) {
        return expertService.convertEntityToModel(expertService.getExpert(id));
    }

    @DeleteMapping("/deleteExpert/{id}")
    public ExpertModelGet deleteExpert(@PathVariable Long id) {
        return expertService.convertEntityToModel(expertService.delete(id));
    }
}
