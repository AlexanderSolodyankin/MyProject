package com.itacademy.controller;

import com.itacademy.entity.ServiceCenterEntity;
import com.itacademy.entity.UserInfo;
import com.itacademy.model.UserAuthModel;
import com.itacademy.service.ServiceCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/serviceCenter")
public class ServiceCenterController {
    @Autowired
    private ServiceCenterService serviceCenterService;

    @GetMapping("/getAll")
    public List<ServiceCenterEntity> getAllService(){
        return serviceCenterService.getAllCerviceCenter();
    }
    @PostMapping("/saveServiceCenter")
    public ServiceCenterEntity saveServiceCenter(@RequestBody ServiceCenterEntity serviceCenterEntity){
        return serviceCenterService.save(serviceCenterEntity);
    }
    @PostMapping("/getService")
    public ServiceCenterEntity serviceCenterEntity (@RequestBody UserAuthModel  userAuthModel){
        return serviceCenterService.getServiceCenter( userAuthModel);
    }
    @DeleteMapping("/deleteService")
    public ServiceCenterEntity deleteService(@RequestBody UserAuthModel userAuthModel){
        return  serviceCenterService.delete(userAuthModel);
    }
    @GetMapping("/getServiceById/{id}")
    public ServiceCenterEntity getUserInfoById(@PathVariable Long id){
        return serviceCenterService.getServiceCenter(id);
    }
}
