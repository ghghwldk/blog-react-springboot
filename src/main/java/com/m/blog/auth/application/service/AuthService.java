package com.m.blog.auth.application.service;


import com.m.blog.common.UseCase;
import com.m.blog.auth.application.domain.Member;
import com.m.blog.auth.application.port.persistence.FindMemberPersistencePort;
import com.m.blog.auth.application.usecase.AuthUsecase;
//import com.m.blog.domain.auth.adapter.out.persistence.Member;
import com.m.blog.global.security.session.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@UseCase
@RequiredArgsConstructor
public class AuthService implements AuthUsecase {
    private final FindMemberPersistencePort findMemberPersistencePort;
    private final SessionUtil sessionUtil;

    @Override
    public void logout(){
        sessionUtil.inValidateSession();
    }

    @Override
    public Member login(Member.LoginInfo info){
        Member member = findMemberPersistencePort.find(info);
        sessionUtil.setAttribute(member);

        return member;
    }

}