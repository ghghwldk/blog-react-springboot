package com.m.blog.domain.auth.adapter.entrypoint.api;

import com.m.blog.common.Mapper;
import com.m.blog.domain.auth.application.domain.Member;
import com.m.blog.domain.auth.infrastructure.web.dto.LoginRequest;

@Mapper
public class MemberMapper {
    public static Member.MemberLoginInfo of(LoginRequest request){
        return Member.MemberLoginInfo.builder()
                .id(Member.MemberId.builder()
                        .userId(request.getUserId())
                        .build())
                .build();
    }
}
