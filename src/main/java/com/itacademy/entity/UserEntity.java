package com.itacademy.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = true)
    private String login;
    @Column(nullable = true)
    private String password;
    @Column(nullable = true)
    private String email;
    private LocalDateTime createDate;

    @PrePersist
    public void prePersistCreateData(){
        this.createDate = LocalDateTime.now();
    }


}
