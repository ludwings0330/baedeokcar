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


    //==relation method==//
    public void setOwner(Member member) {
        owner = member;
        member.addCoupon(this);
    }
}
