package com.project.baedeokcar.domain.dto.member;

import com.project.baedeokcar.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {
    private String loginId;
    private String name;

    public MemberDto(Member member) {
        this.loginId = member.getLoginId();
        this.name = member.getName();
    }
}
