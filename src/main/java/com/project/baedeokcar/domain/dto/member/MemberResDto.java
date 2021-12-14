package com.project.baedeokcar.domain.dto.member;

import com.project.baedeokcar.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberResDto {
    private String loginId;
    private String name;

    // password가 notnull인데? 이걸... 남겨야하나?
    public Member toEntity() {
        return Member.builder()
                .loginId(loginId)
                .name(name)
                .build();
    }

    public MemberResDto(Member entity) {
        this.loginId = entity.getLoginId();
        this.name = entity.getName();
    }
}
