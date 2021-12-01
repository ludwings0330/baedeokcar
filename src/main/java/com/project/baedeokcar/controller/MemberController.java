package com.project.baedeokcar.controller;

import com.project.baedeokcar.aop.PerfLogging;
import com.project.baedeokcar.domain.Member;
import com.project.baedeokcar.domain.dto.MemberDto;
import com.project.baedeokcar.domain.dto.MemberJoinDto;
import com.project.baedeokcar.repository.MemberRepository;
import com.project.baedeokcar.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Slf4j
@Controller
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/join")
    @PerfLogging
    public String join(@ModelAttribute MemberJoinDto member) {
        MemberJoinDto findMember = memberService.findByLoginId(member.getLoginId());

        if (findMember == null) {
            memberService.save(member);
        } else {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberJoinDto member) {
        MemberDto loginMember = memberService.login(member);
        if (loginMember == null) {
            throw new IllegalStateException("아이디 / 비밀번호가 일치하지 않습니다.");
        }

        return "redirect:/";
    }
}
