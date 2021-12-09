package com.project.baedeokcar.service;

import com.project.baedeokcar.domain.dto.MemberDto;
import com.project.baedeokcar.domain.dto.MemberJoinDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {
    public Long save(MemberJoinDto member);

    public MemberJoinDto findByLoginId(String loginId);

    public MemberJoinDto findById(Long id) throws Exception;

    public List<MemberJoinDto> findAll();

    public MemberDto login(MemberJoinDto member);
}
