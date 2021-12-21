package com.itacademy.controller;

import com.itacademy.entity.ServiceCenterEntity;
import com.itacademy.model.serviceCenterModel.ServiceCenterModel;
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
    public List<ServiceCenterModel> getAllService() {
        return serviceCenterService.convertServiceEntityToServiceModelList(
                serviceCenterService.getAllCerviceCenter());
    }

    @PostMapping("/saveServiceCenter")
    public ServiceCenterModel saveServiceCenter(@RequestBody ServiceCenterEntity serviceCenterEntity) {
        return serviceCenterService.convertServiceEntityToServiceModel(
                serviceCenterService.save(serviceCenterEntity));
    }

    @GetMapping("/getService")
    public ServiceCenterModel serviceCenterEntity() {
        return serviceCenterService.convertServiceEntityToServiceModel(
                serviceCenterService.getServiceCenter(usersService.getCurrentUser()));
    }

    @DeleteMapping("/deleteService")
    public ServiceCenterModel deleteService() {
        return serviceCenterService.convertServiceEntityToServiceModel(
                serviceCenterService.delete(usersService.getCurrentUser()));
    }

    @GetMapping("/getServiceById/{id}")
    public ServiceCenterModel getUserInfoById(@PathVariable Long id) {
        return serviceCenterService.convertServiceEntityToServiceModel(
                serviceCenterService.getServiceCenter(id));
    }
}
