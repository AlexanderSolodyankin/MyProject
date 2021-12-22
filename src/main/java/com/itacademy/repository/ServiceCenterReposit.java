package com.itacademy.repository;

import com.itacademy.entity.ServiceCenterEntity;
import com.itacademy.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceCenterReposit extends JpaRepository<ServiceCenterEntity, Long> {
    Optional<List<ServiceCenterEntity>> findByUserEntity(UserEntity userEntity);
}
