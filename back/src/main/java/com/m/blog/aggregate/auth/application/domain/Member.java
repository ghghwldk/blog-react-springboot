package com.m.blog.aggregate.auth.application.domain;

import com.m.blog.global.customAnnotation.Domain;
import com.m.blog.global.customAnnotation.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Domain
@Root
public class Member {
    MemberId memberId;
    String name;
    String password;
    String role;

    public Member(String memberId, String name, String password, String role){
        this.memberId = new MemberId(memberId);
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public static Member withIdAndPassword(MemberId memberId, String password){
        return new Member(memberId, null, password, null);
    }

    @AllArgsConstructor
    @Getter
    public static class MemberId{
        String value;
    }

}
