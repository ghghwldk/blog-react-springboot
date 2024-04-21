package com.m.blog.global.security.session.vo;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class SessionData {
    @NotNull private final String memberId;
    @NotNull private final String role;
}
