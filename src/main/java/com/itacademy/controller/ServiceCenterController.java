package com.itacademy.controller;

import com.itacademy.model.service_center_model.GetServiceCenterModel;
import com.itacademy.model.service_center_model.PostServiceCenterModel;
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
    public List<GetServiceCenterModel> serviceCenterEntity() {
        return serviceCenterService.convertServiceEntityToServiceModelList(
                serviceCenterService.getServiceCenter(usersService.getCurrentUser()));
    }

    @DeleteMapping("/deleteService/{id}")
    public GetServiceCenterModel deleteService(@PathVariable Long id) {
        return serviceCenterService.convertServiceEntityToServiceModel(
                serviceCenterService.delete(id));
    }

    @GetMapping("/getServiceById/{id}")
    public GetServiceCenterModel getUserInfoById(@PathVariable Long id) {
        return serviceCenterService.convertServiceEntityToServiceModel(
                serviceCenterService.getServiceCenter(id));
    }
}
