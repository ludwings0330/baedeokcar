package com.project.baedeokcar.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Car extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "car_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private CarState state;
/*
    private Member owner;
    private Reservation reservInfo;
    */
    private int price;
}
