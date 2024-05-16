package com.m.blog.aggregate.auth.application.port.in;

import com.m.blog.aggregate.auth.application.domain.Member;

import javax.servlet.http.HttpServletRequest;

public interface AuthUsecase {
    void logout();

    Member login(String userId, String password);
}
