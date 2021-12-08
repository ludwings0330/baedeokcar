package com.project.baedeokcar.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Coupon extends BaseTimeEntity {

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", owner=" + owner.getName() +
                ", state=" + state +
                '}';
    }

    @Id @GeneratedValue
    @Column(name = "coupon_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member owner;

    @Enumerated(EnumType.STRING)
    private CouponState state = CouponState.NOT_USED;

    @OneToOne(mappedBy = "coupon")
    private Reservation reservation;

    private int discountPrice = 5000;

    //==relation method==//
    public void setOwner(Member member) {
        owner = member;
        member.addCoupon(this);
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    //==end of relation method==//


    //==business logic==//
    public void useCoupon() {
        this.state = CouponState.USED;
    }

    public void cancelCoupon() {
        this.state = CouponState.NOT_USED;
    }

    public int setDiscountPrice(int price) {
        discountPrice = price;
        return discountPrice;
    }
    //==end of business login==//
}
