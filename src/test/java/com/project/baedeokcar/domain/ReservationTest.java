package com.project.baedeokcar.domain;

import com.project.baedeokcar.repository.ReservationRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Commit
@SpringBootTest
class ReservationTest {
    @PersistenceContext
    private  EntityManager em;

    @Autowired
    private ReservationRepository reservationRepository;


    @BeforeEach
    public void init() {
        Member member = Member.builder()
                .name("배창민")
                .loginId("ludwings")
                .password("1234")
                .build();

        Car car = new Car();

        Reservation reservation = new Reservation();

        Coupon coupon = new Coupon();

        coupon.setOwner(member);
        car.setOwner(member);

        reservation.setCar(car);
        reservation.setMember(member);
        reservation.useCoupon(coupon);


        em.flush();
        em.clear();


        em.persist(member);
        em.persist(car);
        em.persist(reservation);
        em.persist(coupon);

    }

    @Transactional
    @Test
    public void 예약등록() throws Exception {
        //given

        //when
        List<Reservation> all = reservationRepository.findAll();
        for (Reservation r :
                all) {
            System.out.println("r.toString() = " + r.toString());
        }


        //then

    }
}