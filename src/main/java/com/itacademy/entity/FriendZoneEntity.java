package com.itacademy.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "friend_zone")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FriendZoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",unique = true)
    private UserEntity userEntity;
}
