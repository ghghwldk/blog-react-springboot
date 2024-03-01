package com.m.blog.domain.auth.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Id;

@Getter
@AllArgsConstructor
@Builder
public class Member {
    MemberId id;
    String name;
    String password;
    String role;

    @AllArgsConstructor
    @Builder
    @Getter
    public static class MemberId{
        String id;
    }
}
