package com.project.baedeokcar.repository;

import com.project.baedeokcar.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByLoginId(String loginId);
}

