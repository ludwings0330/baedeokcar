package com.project.baedeokcar.domain;

import com.project.baedeokcar.repository.CouponRepository;
import com.project.baedeokcar.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@Commit
@SpringBootTest
class CouponTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private EntityManager em;

    @Transactional
    @Test
    public void 멤버_쿠폰_연관관계() throws Exception {
        //given
        Member member = new Member("couponTest", "test", "test");
        Coupon coupon = new Coupon();
        Coupon coupon2 = new Coupon();
        
        //when
        coupon.setOwner(member);
        coupon2.setOwner(member);

        em.persist(coupon);
        em.persist(coupon2);
        em.persist(member);

        em.flush();
        em.clear();

        //then
        Optional<Member> findMember = memberRepository.findByLoginId(member.getLoginId());

        if (findMember.isPresent()) {
            List<Coupon> couponList = findMember.get().getCouponList();

            for (Coupon c :
                    couponList) {
                System.out.println("c.getId() = " + c.getId());
            }
        } else {
            throw new IllegalStateException("멤버찾기 실패");
        }


        List<Coupon> all = couponRepository.findAll();
        for (Coupon c :
                all) {
            System.out.println("c.toString() = " + c.toString());
        }
    }
}