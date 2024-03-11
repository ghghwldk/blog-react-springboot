package com.m.blog.domain.auth.adapter.entrypoint.api;

import com.m.blog.common.Adapter;
import com.m.blog.domain.auth.application.domain.Member;
import com.m.blog.domain.auth.application.port.entrypoint.api.AuthEndpointPort;
import com.m.blog.domain.auth.application.usecase.AuthUsecase;
import com.m.blog.domain.auth.infrastructure.repository.MemberEntity;
import com.m.blog.domain.auth.infrastructure.web.dto.LoginRequest;
import com.m.blog.domain.auth.infrastructure.web.dto.LoginResponse;
import com.m.blog.global.config.variable.SessionConst;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Adapter
@RequiredArgsConstructor
public class AuthEndpointAdapter implements AuthEndpointPort {
    private final AuthUsecase authUsecase;
    private final MemberMapper memberMapper;

    private void setAttribute(Member member, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);
    }

    @Override
    public LoginResponse login(LoginRequest request, HttpServletRequest httpServletRequest) {
        Member member = authUsecase.login(MemberMapper.of(request));

        this.setAttribute(member, httpServletRequest);

        return LoginResponse.builder()
                .role(member.getRole())
                .build();
    }

    @Override
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}
