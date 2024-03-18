package com.m.blog.domain.auth.application.port.entrypoint.api;

import com.m.blog.domain.auth.infrastructure.web.dto.LoginRequest;
import com.m.blog.domain.auth.infrastructure.web.dto.LoginResponse;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

public interface AuthEndpointPort {
    LoginResponse login(LoginRequest loginRequest);

    String logout(HttpServletRequest request);
}
