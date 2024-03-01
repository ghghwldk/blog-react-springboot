package com.m.blog.domain.auth.application.service;


import com.m.blog.domain.auth.application.usecase.AuthUsecase;
import com.m.blog.domain.auth.infrastructure.web.dto.LoginRequest;
import com.m.blog.domain.auth.infrastructure.web.dto.LoginResponse;
//import com.m.blog.domain.auth.adapter.out.persistence.Member;
import com.m.blog.domain.auth.adapter.persistence.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUsecase {
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public void logout(HttpServletRequest request){
        HttpSession session= request.getSession(false);
        if(session!=null){
            session.invalidate();
        }
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest, HttpServletRequest request){
//        Member loginMember= memberJpaRepository
//                .findMemberByIdAndPassword(loginRequest.getUserId(), loginRequest.getPassword())
//                .orElseThrow(RuntimeException::new);
//
//        request.getSession()
//                .setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
//
//        return LoginResponse.builder()
//                .role(loginMember.getRole())
//                .build();
        return null;
    }

}
