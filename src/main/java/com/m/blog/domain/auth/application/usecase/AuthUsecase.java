package com.m.blog.domain.auth.application.usecase;

import com.m.blog.domain.auth.application.domain.Member;
import com.m.blog.domain.auth.infrastructure.web.dto.LoginRequest;
import com.m.blog.domain.auth.infrastructure.web.dto.LoginResponse;

import javax.servlet.http.HttpServletRequest;

public interface AuthUsecase {
    void logout(HttpServletRequest request);

    Member login(Member.MemberLoginInfo loginInfo);
}
