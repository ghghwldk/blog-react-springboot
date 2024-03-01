package com.m.blog.domain.auth.application.service;


import com.m.blog.domain.auth.adapter.persistence.MemberJpaMapper;
import com.m.blog.domain.auth.application.domain.Member;
import com.m.blog.domain.auth.application.port.persistence.FindMemberPort;
import com.m.blog.domain.auth.application.usecase.AuthUsecase;
import com.m.blog.domain.auth.infrastructure.web.dto.LoginRequest;
import com.m.blog.domain.auth.infrastructure.web.dto.LoginResponse;
//import com.m.blog.domain.auth.adapter.out.persistence.Member;
import com.m.blog.domain.auth.infrastructure.repository.MemberJpaRepository;
import com.m.blog.global.config.variable.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUsecase {
    private final FindMemberPort findMemberPort;

    @Override
    public void logout(HttpServletRequest request){
        HttpSession session= request.getSession(false);
        if(session!=null){
            session.invalidate();
        }
    }

    @Override
    public LoginResponse login(LoginRequest request, HttpServletRequest httpServletRequest){
        Member loginMember =
                findMemberPort.find(request.getUserId(), request.getPassword());

        httpServletRequest.getSession()
                .setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return MemberJpaMapper.of(loginMember);
    }

}
