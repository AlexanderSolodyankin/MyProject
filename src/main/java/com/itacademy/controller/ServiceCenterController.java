package com.itacademy.controller;

import com.itacademy.entity.ServiceCenterEntity;
import com.itacademy.model.serviceCenterModel.GetServiceCenterModel;
import com.itacademy.model.serviceCenterModel.PostServiceCenterModel;
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
    public List<GetServiceCenterModel> getAllService() {
        return serviceCenterService.convertServiceEntityToServiceModelList(
                serviceCenterService.getAllCerviceCenter());
    }

    @PostMapping("/saveServiceCenter")
    public GetServiceCenterModel saveServiceCenter(@RequestBody PostServiceCenterModel postServiceCenterModel) {
        return serviceCenterService.convertServiceEntityToServiceModel(
                serviceCenterService.save(postServiceCenterModel));
    }

    @GetMapping("/getService")
    public GetServiceCenterModel serviceCenterEntity() {
        return serviceCenterService.convertServiceEntityToServiceModel(
                serviceCenterService.getServiceCenter(usersService.getCurrentUser()));
    }

    @DeleteMapping("/deleteService")
    public GetServiceCenterModel deleteService() {
        return serviceCenterService.convertServiceEntityToServiceModel(
                serviceCenterService.delete(usersService.getCurrentUser()));
    }

    @GetMapping("/getServiceById/{id}")
    public GetServiceCenterModel getUserInfoById(@PathVariable Long id) {
        return serviceCenterService.convertServiceEntityToServiceModel(
                serviceCenterService.getServiceCenter(id));
    }
}
