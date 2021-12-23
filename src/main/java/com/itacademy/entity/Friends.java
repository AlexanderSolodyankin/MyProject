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
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
