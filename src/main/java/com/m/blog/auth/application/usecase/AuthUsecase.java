package com.m.blog.auth.application.usecase;

import com.m.blog.auth.application.domain.Member;

import javax.servlet.http.HttpServletRequest;

public interface AuthUsecase {
    void logout();

    Member login(Member.LoginInfo loginInfo);
}
