package com.project.baedeokcar.domain.dto.member;

import com.project.baedeokcar.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class
MemberJoinDto {
    private String loginId;
    private String name;
    private String password;

    public Member toEntity() {
        return new Member(loginId, name, password);
    }

    public MemberJoinDto(Member entity) {
        this.loginId = entity.getLoginId();
        this.name = entity.getName();
        this.password = entity.getPassword();
    }
}
