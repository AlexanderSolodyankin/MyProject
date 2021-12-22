package com.itacademy.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "commentary")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PublicationCommentaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String values;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private PublicationUsersEntity publicationUsersEntity;
    private LocalDateTime createData;

    @PrePersist
    public void prePersistCreateData() {
        this.createData = LocalDateTime.now();
    }


}
