package com.m.blog.aggregate.auth.adapter.in.web;

import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.auth.application.domain.Member;
import com.m.blog.aggregate.auth.application.port.in.AuthEndpointPort;
import com.m.blog.aggregate.auth.application.usecase.AuthUsecase;
import com.m.blog.aggregate.auth.infrastructure.web.dto.SigninRequest;
import com.m.blog.aggregate.auth.infrastructure.web.dto.SigninResponse;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class AuthEndpointAdapter implements AuthEndpointPort {
    private final AuthUsecase authUsecase;

    @Override
    public SigninResponse login(SigninRequest request) {
        Member member = authUsecase.login(MemberEntrypointMapper.of(request));

        return SigninResponse.builder()
                .role(member.getRole())
                .build();
    }

    @Override
    public void logout() {
        authUsecase.logout();
    }
}
