package com.project.baedeokcar.domain;

import com.sun.istack.NotNull;
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

    @NotNull
    @Column(unique = true)
    private String loginId;
    @NotNull
    private String name;
    @NotNull
    private String password;

    @OneToMany(mappedBy = "owner")
    private List<Car> carList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Reservation> reservationList = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    private List<Coupon> couponList = new ArrayList<>();

    @Builder
    public Member(String loginId, String name, String password) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }

    //==relation method==//
    public void addCoupon(Coupon coupon) {
        couponList.add(coupon);
    }

    public void carRegistration(Car car) {
        carList.add(car);
    }

    public void addReservation(Reservation reservation) {
        this.reservationList.add(reservation);
    }


    //==end of relation method==//

    //==business logic==//
    public void changeInfo(Member member) {
        this.loginId = member.getLoginId();
        this.name = member.getName();
        this.password = member.getPassword();
    }


    //==end of business logic==//

}
