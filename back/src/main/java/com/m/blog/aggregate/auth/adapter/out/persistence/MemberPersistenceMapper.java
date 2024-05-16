package com.m.blog.aggregate.auth.adapter.out.persistence;

import com.m.blog.aggregate.auth.application.domain.Member;
import com.m.blog.aggregate.auth.adapter.in.web.SigninResponse;
import com.m.blog.global.customAnnotation.Mapper;

@Mapper
class MemberPersistenceMapper {
    public static Member of(MemberEntity entity){
        return new Member(entity.getId(), entity.getName(), entity.getPassword(), entity.getRole());
    }

    public static SigninResponse of(Member loginMember){
        return SigninResponse.builder()
                .role(loginMember.getRole())
                .build();
    }
}
