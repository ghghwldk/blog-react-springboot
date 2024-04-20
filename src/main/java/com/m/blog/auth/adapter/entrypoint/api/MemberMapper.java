package com.m.blog.auth.adapter.entrypoint.api;

import com.m.blog.auth.application.domain.Member;
import com.m.blog.auth.infrastructure.web.dto.LoginRequest;
import com.m.blog.common.Mapper;

@Mapper
class MemberMapper {
    public static Member.LoginInfo of(LoginRequest request){
        return Member.LoginInfo.builder()
                .memberId(Member.MemberId.builder()
                        .value(request.getUserId())
                        .build())
                .password(request.getPassword())
                .build();
    }
}
