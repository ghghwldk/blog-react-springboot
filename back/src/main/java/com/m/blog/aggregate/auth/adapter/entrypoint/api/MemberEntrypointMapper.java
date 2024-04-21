package com.m.blog.aggregate.auth.adapter.entrypoint.api;

import com.m.blog.aggregate.auth.application.domain.Member;
import com.m.blog.aggregate.auth.infrastructure.web.dto.LoginRequest;
import com.m.blog.global.customAnnotation.Mapper;

@Mapper
class MemberEntrypointMapper {
    public static Member.LoginInfo of(LoginRequest request){
        return Member.LoginInfo.builder()
                .memberId(Member.MemberId.builder()
                        .value(request.getUserId())
                        .build())
                .password(request.getPassword())
                .build();
    }
}
