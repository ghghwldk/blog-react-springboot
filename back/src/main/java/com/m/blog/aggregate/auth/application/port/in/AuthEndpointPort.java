package com.m.blog.aggregate.auth.application.port.in;

import com.m.blog.aggregate.auth.adapter.in.web.SigninRequest;
import com.m.blog.aggregate.auth.adapter.in.web.SigninResponse;

public interface AuthEndpointPort {
    SigninResponse login(SigninRequest signinRequest);

    void logout();
}
