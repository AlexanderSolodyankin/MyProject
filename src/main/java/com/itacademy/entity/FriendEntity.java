package com.itacademy.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "friend")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FriendEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "friend_zone_id")
    private FriendZoneEntity friendZoneEntity;

}
