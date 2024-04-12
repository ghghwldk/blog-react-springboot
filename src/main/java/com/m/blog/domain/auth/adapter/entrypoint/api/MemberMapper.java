package com.m.blog.domain.auth.adapter.entrypoint.api;

import com.m.blog.domain.auth.application.domain.Member;
import com.m.blog.domain.auth.infrastructure.web.dto.LoginRequest;

public class MemberMapper {
    public static Member.LoginInfo of(LoginRequest request){
        return Member.LoginInfo.builder()
                .memberId(Member.MemberId.builder()
                        .value(request.getUserId())
                        .build())
                .password(request.getPassword())
                .build();
    }
}
