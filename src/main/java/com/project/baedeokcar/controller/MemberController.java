package com.project.baedeokcar.controller;

import com.project.baedeokcar.aop.PerfLogging;
import com.project.baedeokcar.domain.dto.member.MemberDto;
import com.project.baedeokcar.domain.dto.member.MemberJoinDto;
import com.project.baedeokcar.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Slf4j
@Controller
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members")
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


    @PostMapping("/check-id")
    @ResponseBody
    public MemberJoinDto checkId(@RequestParam String loginId) {
        MemberJoinDto findMember = memberService.findByLoginId(loginId);

        return findMember;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberJoinDto member, HttpSession session) {
        MemberDto loginMember = memberService.login(member);

        if (loginMember == null) {
            return "login";
        }

        session.setAttribute("authInfo", loginMember);
        session.setMaxInactiveInterval(60 * 10);


        return "redirect:/";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/";
    }
}
