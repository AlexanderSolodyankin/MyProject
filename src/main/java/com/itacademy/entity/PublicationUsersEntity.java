package com.itacademy.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PublicationUsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createData;
    private String postValue;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @PrePersist
    public void prePersistCreateData() {
        this.createData = LocalDateTime.now();
    }
}