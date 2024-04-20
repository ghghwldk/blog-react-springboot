package com.m.blog.auth.adapter.persistence;

import com.m.blog.auth.application.domain.Member;
import com.m.blog.auth.infrastructure.repository.MemberEntity;
import com.m.blog.auth.infrastructure.web.dto.LoginResponse;

public class MemberPersistenceMapper {
    public static Member of(MemberEntity entity){
        return Member.builder()
                .memberId(Member.MemberId.builder()
                        .value(entity.getId())
                        .build())
                .name(entity.getName())
                .password(entity.getPassword())
                .role(entity.getRole())
                .build();
    }

    public static LoginResponse of(Member loginMember){
        return LoginResponse.builder()
                .role(loginMember.getRole())
                .build();
    }
}
