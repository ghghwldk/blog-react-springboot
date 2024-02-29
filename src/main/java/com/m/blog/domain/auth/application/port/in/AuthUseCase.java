package com.m.blog.domain.auth.application.port.in;

import com.m.blog.domain.auth.application.port.out.LoginResponse;

import javax.servlet.http.HttpServletRequest;

public interface AuthUseCase {
    void logout(HttpServletRequest request);

    LoginResponse login(LoginRequest loginRequest, HttpServletRequest request);
}
