package com.itacademy.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messege")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessegUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private LocalDateTime createDate;

    @PrePersist
    public void prePersistCreateData(){
        this.createDate = LocalDateTime.now();
    }
}
