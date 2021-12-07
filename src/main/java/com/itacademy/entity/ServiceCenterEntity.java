package com.itacademy.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "service_center")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServiceCenterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

}
