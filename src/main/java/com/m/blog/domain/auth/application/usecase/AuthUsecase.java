package com.m.blog.domain.auth.application.usecase;

import com.m.blog.domain.auth.application.domain.Member;

import javax.servlet.http.HttpServletRequest;

public interface AuthUsecase {
    void logout();

    Member login(Member.LoginInfo loginInfo);
}
