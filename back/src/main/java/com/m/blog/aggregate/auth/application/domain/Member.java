package com.m.blog.aggregate.auth.application.domain;

import com.m.blog.global.customAnnotation.Domain;
import com.m.blog.global.customAnnotation.Root;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@Domain
@Root
public class Member {
    MemberId memberId;
    String name;
    String password;
    String role;

    @AllArgsConstructor
    @Builder
    @Getter
    public static class MemberId{
        String value;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class LoginInfo {
        MemberId memberId;
        String password;
    }
}
