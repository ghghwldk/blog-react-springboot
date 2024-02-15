package com.m.blog.domain.auth.service;


import com.m.blog.domain.auth.dto.LoginRequest;
import com.m.blog.domain.auth.dto.LoginResponse;
import com.m.blog.domain.auth.entity.Member;
import com.m.blog.domain.auth.repository.MemberJpaRepository;
import com.m.blog.global.config.variable.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
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
        LoginResponse.LoginResponseBuilder builder = LoginResponse.builder();

        Member loginMember= memberJpaRepository.findMemberByIdAndPassword(loginRequest.getUserId(), loginRequest.getPassword())
                .orElseThrow(RuntimeException::new);

        // if entered information is about the member, then we have to process the login
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        builder.role(loginMember.getRole());

        return builder.build();
    }

}
