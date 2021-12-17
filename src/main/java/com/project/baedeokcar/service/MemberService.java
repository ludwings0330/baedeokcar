package com.project.baedeokcar.service;

import com.project.baedeokcar.domain.Member;
import com.project.baedeokcar.domain.dto.member.MemberDto;
import com.project.baedeokcar.domain.dto.member.MemberJoinDto;
import com.project.baedeokcar.domain.dto.member.MemberReqDto;
import com.project.baedeokcar.domain.dto.member.MemberResDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {

    // 회원 가입
    public Long save(MemberJoinDto member);

    public MemberJoinDto findByLoginId(String loginId);

    public MemberJoinDto findById(Long id) throws Exception;

    // 회원 목록
    public List<MemberJoinDto> getMembers();

    // 로그인
    public MemberDto login(MemberJoinDto member);

    //회원 탈퇴
    public boolean quit(MemberReqDto member);


    //회원 정보 수정
    public MemberResDto modifyMemberInfo(MemberReqDto member);

    Member findOneByLoginId(String id);
}
