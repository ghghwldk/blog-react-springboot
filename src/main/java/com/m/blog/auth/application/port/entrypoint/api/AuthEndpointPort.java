package com.m.blog.auth.application.port.entrypoint.api;

import com.m.blog.auth.infrastructure.web.dto.LoginRequest;
import com.m.blog.auth.infrastructure.web.dto.LoginResponse;
import com.m.blog.auth.infrastructure.web.dto.LogoutResponse;

import javax.servlet.http.HttpServletRequest;

public interface AuthEndpointPort {
    LoginResponse login(LoginRequest loginRequest);

    LogoutResponse logout(HttpServletRequest request);
}
