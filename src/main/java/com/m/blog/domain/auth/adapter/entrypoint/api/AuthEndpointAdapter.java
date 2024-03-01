package com.m.blog.domain.auth.adapter.entrypoint.api;

import com.m.blog.common.Adapter;
import com.m.blog.domain.auth.application.port.entrypoint.api.AuthEndpointPort;
import com.m.blog.domain.auth.application.usecase.AuthUsecase;
import com.m.blog.domain.auth.infrastructure.web.dto.LoginRequest;
import com.m.blog.domain.auth.infrastructure.web.dto.LoginResponse;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@Adapter
@RequiredArgsConstructor
public class AuthEndpointAdapter implements AuthEndpointPort {
    private final AuthUsecase authUsecase;


    @Override
    public LoginResponse login(LoginRequest loginRequest, HttpServletRequest request) {
        return null;
    }

    @Override
    public void logout(HttpServletRequest request) {

    }
}
