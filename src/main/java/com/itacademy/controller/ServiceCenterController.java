package com.itacademy.controller;

import com.itacademy.entity.ServiceCenterEntity;
import com.itacademy.service.ServiceCenterService;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/serviceCenter")
public class ServiceCenterController {
    @Autowired
    private ServiceCenterService serviceCenterService;
    @Autowired
    private UsersService usersService;

    @GetMapping("/getAll")
    public List<ServiceCenterEntity> getAllService(){
        return serviceCenterService.getAllCerviceCenter();
    }

    @PostMapping("/saveServiceCenter")
    public ServiceCenterEntity saveServiceCenter(@RequestBody ServiceCenterEntity serviceCenterEntity){
        return serviceCenterService.save(serviceCenterEntity);
    }
    @GetMapping("/getService")
    public ServiceCenterEntity serviceCenterEntity (){
        return serviceCenterService.getServiceCenter(usersService.getCurrentUser());
    }
    @DeleteMapping("/deleteService")
    public ServiceCenterEntity deleteService(){
        return  serviceCenterService.delete(usersService.getCurrentUser());
    }
    @GetMapping("/getServiceById/{id}")
    public ServiceCenterEntity getUserInfoById(@PathVariable Long id){
        return serviceCenterService.getServiceCenter(id);
    }
}
