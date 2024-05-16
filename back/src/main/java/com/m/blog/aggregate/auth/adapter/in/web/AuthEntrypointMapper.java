package com.m.blog.aggregate.auth.adapter.in.web;

import com.m.blog.aggregate.auth.application.domain.Member;

public class AuthEntrypointMapper {
    static SigninResponse from(Member member){
        return SigninResponse.builder()
                .role(member.getRole())
                .build();
    }
}
