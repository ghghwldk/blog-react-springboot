package com.m.blog.aggregate.auth.application.port.in;

import com.m.blog.aggregate.auth.infrastructure.web.dto.SigninRequest;
import com.m.blog.aggregate.auth.infrastructure.web.dto.SigninResponse;

public interface AuthEndpointPort {
    SigninResponse login(SigninRequest signinRequest);

    void logout();
}
