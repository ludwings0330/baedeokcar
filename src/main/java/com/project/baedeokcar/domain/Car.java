package com.project.baedeokcar.domain;

import com.project.baedeokcar.domain.dto.car.CarModReqDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "car_id")
    private Long id;

    private int driveDistance;   // 주행거리
    private String year; // 년식

    private String info;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private CarState state = CarState.NOT_IN_USE;

    // 회원은 여러대의 차량을 소유한다, 차량은 한명의 소유자를 가진다.
    // 차량에 소유주 번호가 있다. -> 연관관계의 주인 = 차량
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member owner;

    // 차량은 여러 예약정보를 가지고 있다.
    // 예약에서 한대의 차량을 가지고 있다.
    @OneToMany(mappedBy = "car")
    private List<Reservation> reservationList = new ArrayList<>();

    private int price;

    private String originFileName;
    private String savedFileName;


    //==relation method==//
    public void setOwner(Member member) {
        owner = member;
        member.carRegistration(this);
    }


    public void addReservation(Reservation reservation) {
        reservationList.add(reservation);
    }
    //==end of relation method==//

    //==business logic==//
    public int changePricePerHour(int price) {
        this.price = price;
        return this.getPrice();
    }

    public void changeCarInfo(CarModReqDto car) {
        this.price = car.getPrice();
        this.info = car.getInfo();
    }
    public int getPricePerHour() {
        return price;
    }

    public void useCar() {
        this.state = CarState.IN_USE;
    }

    public void returnCar() {
        this.state = CarState.NOT_IN_USE;
    }
    //==end of business logic==//
}
