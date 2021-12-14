package com.itacademy.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_info")
@ToString
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String serName;
    private String patronymic;
    private String phone;
    private String country;
    private String city;
    private String car;
    private boolean gender;
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private UserEntity userEntity;

}
