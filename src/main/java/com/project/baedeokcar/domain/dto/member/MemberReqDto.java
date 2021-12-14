package com.project.baedeokcar.domain.dto.member;

import com.project.baedeokcar.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberReqDto {
    private String loginId;
    private String name;
    private String password;

    public Member toEntity() {
        return Member.builder()
                .loginId(loginId)
                .name(name)
                .password(password)
                .build();
    }

    public MemberReqDto(Member entity) {
        this.loginId = entity.getLoginId();
        this.name = entity.getName();
        this.password = entity.getPassword();
    }
}
