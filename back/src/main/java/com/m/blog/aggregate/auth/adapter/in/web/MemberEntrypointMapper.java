package com.m.blog.aggregate.auth.adapter.in.web;

import com.m.blog.aggregate.auth.application.domain.Member;
import com.m.blog.global.customAnnotation.Mapper;

@Mapper
class MemberEntrypointMapper {
    public static Member of(SigninRequest request){
        return Member.withIdAndPassword(new Member.MemberId(request.getUserId()),
                request.getPassword());
    }
}
