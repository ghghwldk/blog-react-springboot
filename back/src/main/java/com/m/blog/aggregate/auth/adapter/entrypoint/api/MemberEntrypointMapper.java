package com.m.blog.aggregate.auth.adapter.entrypoint.api;

import com.m.blog.aggregate.auth.application.domain.Member;
import com.m.blog.aggregate.auth.infrastructure.web.dto.SigninRequest;
import com.m.blog.global.customAnnotation.Mapper;

@Mapper
class MemberEntrypointMapper {
    public static Member of(SigninRequest request){
        return Member.withIdAndPassword(new Member.MemberId(request.getUserId()),
                request.getPassword());
    }
}
