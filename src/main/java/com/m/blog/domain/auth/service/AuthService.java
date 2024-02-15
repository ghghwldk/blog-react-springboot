package com.m.blog.domain.auth.service;

import com.m.blog.domain.auth.dto.LoginRequest;
import com.m.blog.domain.auth.dto.LoginResponse;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    void logout(HttpServletRequest request);

    LoginResponse login(LoginRequest loginRequest, HttpServletRequest request);
}
