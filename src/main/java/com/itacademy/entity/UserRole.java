package com.itacademy.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_role")
    private String roleName;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

}
