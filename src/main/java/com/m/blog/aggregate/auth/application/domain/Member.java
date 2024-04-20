package com.m.blog.aggregate.auth.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
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
