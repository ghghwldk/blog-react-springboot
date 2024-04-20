package com.m.blog.aggregate.auth.adapter.persistence;

import com.m.blog.aggregate.auth.application.domain.Member;
import com.m.blog.aggregate.auth.infrastructure.repository.MemberEntity;
import com.m.blog.aggregate.auth.infrastructure.web.dto.LoginResponse;
import com.m.blog.common.Mapper;

@Mapper
class MemberPersistenceMapper {
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
