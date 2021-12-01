package com.project.baedeokcar.repository;

import com.project.baedeokcar.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    
    @Test
    public void member_save() throws Exception {
        //given
        Member member = Member.builder()
                .loginId("ludwings")
                .name("name")
                .password("password")
                .build();
        //when
        memberRepository.save(member);

        //then
        List<Member> members = memberRepository.findAll();
        Assertions.assertThat(member.getId()).isEqualTo(members.get(0).getId());
    }
}