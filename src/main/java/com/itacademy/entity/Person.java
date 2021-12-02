package com.itacademy.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "person")
@ToString
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String serName;
    private String patronymic;
//    private Date dataBerth;
    private String phone;
    private String country;
    private String city;
    private String car;
    private boolean gender;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

}
