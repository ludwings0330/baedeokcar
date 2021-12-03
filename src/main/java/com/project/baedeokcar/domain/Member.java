package com.project.baedeokcar.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@AllArgsConstructor
public class Member extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String loginId;
    private String name;
    private String password;

    /*
    // cars that member is owner
    private List<Car> carList;


    // reservation list
    private List<Reservation> reservationList;

    // history of using
    private List<History> historyList;
    */

    // coupons that member has

    @OneToMany(mappedBy = "owner")
    private List<Coupon> couponList = new ArrayList<>();

    @Builder
    public Member(String loginId, String name, String password) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }

    public void addCoupon(Coupon coupon) {
        couponList.add(coupon);
    }
}
