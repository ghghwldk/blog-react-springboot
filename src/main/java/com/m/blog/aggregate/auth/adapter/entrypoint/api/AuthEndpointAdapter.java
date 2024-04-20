package com.m.blog.aggregate.auth.adapter.entrypoint.api;

import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.auth.application.domain.Member;
import com.m.blog.aggregate.auth.application.port.entrypoint.api.AuthEndpointPort;
import com.m.blog.aggregate.auth.application.usecase.AuthUsecase;
import com.m.blog.aggregate.auth.infrastructure.web.dto.LoginRequest;
import com.m.blog.aggregate.auth.infrastructure.web.dto.LoginResponse;
import com.m.blog.aggregate.auth.infrastructure.web.dto.LogoutResponse;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@Adapter
@RequiredArgsConstructor
public class AuthEndpointAdapter implements AuthEndpointPort {
    private final AuthUsecase authUsecase;

    @Override
    public LoginResponse login(LoginRequest request) {
        Member member = authUsecase.login(MemberEntrypointMapper.of(request));

        return LoginResponse.builder()
                .role(member.getRole())
                .build();
    }

    @Override
    public LogoutResponse logout(HttpServletRequest request) {
        authUsecase.logout();

        return LogoutResponse.builder().build();
    }
}
