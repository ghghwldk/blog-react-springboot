package com.m.blog.aggregate.auth.application.usecase;

import com.m.blog.aggregate.auth.application.domain.Member;

import javax.servlet.http.HttpServletRequest;

public interface AuthUsecase {
    void logout();

    Member login(Member member);
}
