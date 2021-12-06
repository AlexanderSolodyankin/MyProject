package com.itacademy.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "expert")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExpertPersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String name;
    private String expertInfo;

    @ManyToMany
    @JoinColumn(name = "person_id")
    private Person person;


}
