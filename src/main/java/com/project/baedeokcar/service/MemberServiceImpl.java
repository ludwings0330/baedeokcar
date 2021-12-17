package com.project.baedeokcar.service;

import com.project.baedeokcar.domain.Member;
import com.project.baedeokcar.domain.dto.member.MemberDto;
import com.project.baedeokcar.domain.dto.member.MemberJoinDto;
import com.project.baedeokcar.domain.dto.member.MemberReqDto;
import com.project.baedeokcar.domain.dto.member.MemberResDto;
import com.project.baedeokcar.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long
    save(MemberJoinDto member) {
        memberRepository.save(member.toEntity());
        return null;
    }

    @Override
    public MemberJoinDto findByLoginId(String loginId) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);

        if (findMember.isPresent()) {
            return new MemberJoinDto(findMember.get());
        } else {
            return null;
        }
    }

    @Override
    public MemberJoinDto findById(Long id) throws Exception {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            return new MemberJoinDto(member.get());
        } else {
            throw new Exception();
        }
    }

    @Override
    public List<MemberJoinDto> getMembers() {
        List<Member> all = memberRepository.findAll();
        List<MemberJoinDto> collect = all.stream()
                .map((m) -> new MemberJoinDto(m))
                .collect(Collectors.toList());

        return collect;
    }

    @Override
    public MemberDto login(MemberJoinDto member) {
        Optional<Member> findMember = memberRepository.findByLoginId(member.getLoginId());
        if (findMember.isPresent()) {
            return new MemberDto(findMember.get());
        }
        return null;
    }

    // 회원 탈퇴
    @Override
    public boolean quit(MemberReqDto member) {
        Optional<Member> findMember = memberRepository.findByLoginId(member.getLoginId());
        memberRepository.delete(findMember.get());

        return true;
    }

    //회원 정보 수정
    @Override
    @Transactional
    public MemberResDto modifyMemberInfo(MemberReqDto member) {
        Optional<Member> findMember = memberRepository.findByLoginId(member.getLoginId());
        findMember.get().changeInfo(member.toEntity());

        return new MemberResDto(findMember.get());
    }

    private boolean verificationMember(MemberJoinDto requestMember, Member findMember) {
        boolean ret = true;
        if (!requestMember.getPassword().equals(findMember.getPassword()))
            ret = false;

        return ret;
    }

    @Override
    public Member findOneByLoginId(String id) {
        Optional<Member> findMember = memberRepository.findByLoginId(id);

        if (findMember.isPresent()) {
            return findMember.get();
        }

        return null;
    }
}
