package com.m.blog.domain.auth.adapter.entrypoint.api;

import com.m.blog.common.Adapter;
import com.m.blog.domain.auth.application.domain.Member;
import com.m.blog.domain.auth.application.port.entrypoint.api.AuthEndpointPort;
import com.m.blog.domain.auth.application.usecase.AuthUsecase;
import com.m.blog.domain.auth.infrastructure.web.dto.LoginRequest;
import com.m.blog.domain.auth.infrastructure.web.dto.LoginResponse;
import com.m.blog.domain.auth.infrastructure.web.dto.LogoutResponse;
import com.m.blog.global.security.session.SessionUtil;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@Adapter
@RequiredArgsConstructor
public class AuthEndpointAdapter implements AuthEndpointPort {
    private final AuthUsecase authUsecase;

    @Override
    public LoginResponse login(LoginRequest request) {
        Member member = authUsecase.login(MemberMapper.of(request));

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
