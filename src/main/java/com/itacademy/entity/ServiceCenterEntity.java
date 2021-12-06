package com.itacademy.entity;

import lombok.*;

import javax.persistence.*;

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

    @ManyToMany
    @JoinColumn(name = "person_id")
    private Person person;

}
