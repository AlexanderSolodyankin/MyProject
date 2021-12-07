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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;


}
