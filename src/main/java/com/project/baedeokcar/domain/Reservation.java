package com.project.baedeokcar.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "reservation_id")
    private Long id;

    // 회원은 여러 예약을 할 수 있다.
    // 예약은 한명의 예약자를 가진다.
    // 연관관계 주인 o
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 예약에는 한대의 자동차를 예약한다.
    // 차는 여러개의 예약정보를 가진다.
    // 연관관계 주인 o
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    // 예약에는 한개의 쿠폰 사용가능,
    // 쿠폰은 한개의 예약에만 사용가능
    // 연관관계 주인 o
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;


    @Enumerated(EnumType.STRING)
    private ReservationState state = ReservationState.RESERVED;

    private LocalDate start;
    private LocalDate end;

    //==relation method==//
    public void setMember(Member member) {
        this.member = member;
        member.addReservation(this);
    }

    public void useCoupon(Coupon coupon) {
        this.coupon = coupon;
        coupon.setReservation(this);
    }

    public void setCar(Car car) {
        this.car = car;
        car.addReservation(this);
    }

    //==end of relation method==//
    public void setMemberAndCarRelation(Member member, Car car) {
        this.member = member;
        member.addReservation(this);

        this.car = car;
        car.addReservation(this);
    }


    //==business logic==//


    public void cancelReservation() {
        this.state = ReservationState.CANCEL;
    }

    public void startReservation() {
        this.state = ReservationState.IN_USE;
    }

    public void returnReservation() {
        this.state = ReservationState.RETURNED;
    }

    public int getPrice() {
        // useTime 은 예약에 따라 계산되도록 변경해야함.
        Period between = Period.between(start, end);
        return car.getPrice() * between.getDays();
    }
    //==end of business logic==//
}
