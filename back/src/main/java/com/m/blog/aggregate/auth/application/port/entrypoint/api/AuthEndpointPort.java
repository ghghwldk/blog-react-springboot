package com.m.blog.aggregate.auth.application.port.entrypoint.api;

import com.m.blog.aggregate.auth.infrastructure.web.dto.LoginRequest;
import com.m.blog.aggregate.auth.infrastructure.web.dto.LoginResponse;

public interface AuthEndpointPort {
    LoginResponse login(LoginRequest loginRequest);

    void logout();
}
