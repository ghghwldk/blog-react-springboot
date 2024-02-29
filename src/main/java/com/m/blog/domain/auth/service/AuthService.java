package com.m.blog.domain.auth.service;


import com.m.blog.domain.auth.application.port.in.AuthUseCase;
import com.m.blog.domain.auth.application.port.in.LoginRequest;
import com.m.blog.domain.auth.application.port.out.LoginResponse;
//import com.m.blog.domain.auth.adapter.out.persistence.Member;
import com.m.blog.domain.auth.adapter.out.persistence.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {
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
