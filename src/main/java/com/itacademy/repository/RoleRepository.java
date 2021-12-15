package com.itacademy.repository;

import com.itacademy.entity.UserEntity;
import com.itacademy.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByUserEntity(UserEntity userEntity);
}
