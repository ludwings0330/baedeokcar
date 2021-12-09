package com.project.baedeokcar.service;

import com.project.baedeokcar.domain.Car;
import com.project.baedeokcar.domain.CarState;
import com.project.baedeokcar.domain.Member;
import com.project.baedeokcar.domain.dto.MemberJoinDto;
import com.project.baedeokcar.repository.CarRepository;
import com.project.baedeokcar.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@Commit
@SpringBootTest
class CarServiceImplTest {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CarService carService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    @Test
    public void 차량등록및삭제() throws Exception {
        //given
        Member findMember = Member.builder()
                .loginId("ludwings")
                .password("1234")
                .name("ckdals")
                .build();

        memberService.save(new MemberJoinDto(findMember));

        em.flush();
        em.clear();

        Car car = Car.builder()
                .price(1000)
                .state(CarState.NOT_IN_USE)
                .owner(memberRepository.findByLoginId("ludwings").get())
                .build();

        carRepository.save(car); // 여기서 등록을 하니까 멤버도 등록 되는거야. case Cade 공부


       //when
        carService.modifyCar(car.getId(), 20000);

        //then

    }
}