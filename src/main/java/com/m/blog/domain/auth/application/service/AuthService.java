package com.m.blog.domain.auth.application.service;


import com.m.blog.domain.auth.application.domain.Member;
import com.m.blog.domain.auth.application.port.persistence.FindMemberPort;
import com.m.blog.domain.auth.application.usecase.AuthUsecase;
//import com.m.blog.domain.auth.adapter.out.persistence.Member;
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
    public Member login(Member.LoginInfo info){
        return findMemberPort
                .find(info);
    }

}
