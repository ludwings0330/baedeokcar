package com.project.baedeokcar.repository;

import com.project.baedeokcar.domain.Car;
import com.project.baedeokcar.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarRepositoryTest {
    @Autowired
    private CarRepository carRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    public void 회원별차량조회() throws Exception {
        //given
        Member memberA = Member.builder()
                .loginId("ludwings")
                .password("1234")
                .name("ckdals")
                .build();

        Member memberB = Member.builder()
                .loginId("papago")
                .password("1234")
                .name("papago")
                .build();

        Car carA = Car.builder()
                .price(1000)
                .info("CarA")
                .build();
        Car carB = Car.builder()
                .price(1000)
                .info("CarB")
                .build();

        Car carC = Car.builder()
                .price(1000)
                .info("CarC")
                .build();

        carA.setOwner(memberA);
        carB.setOwner(memberA);
        carC.setOwner(memberB);
        //when

        em.persist(carA);
        em.persist(carB);
        em.persist(carC);


        em.flush();
        em.clear();
        //then

        List<Car> ownCar = carRepository.findOwnCar(memberA.getLoginId());

        for (Car c :
                ownCar) {
            System.out.println("ownCar.getInfo() = " + c.getInfo());
        }

        List<Car> ownCar2 = carRepository.findOwnCar(memberB.getLoginId());

        for (Car c :
                ownCar2) {
            System.out.println("ownCar.getInfo() = " + c.getInfo());
        }
    }
}