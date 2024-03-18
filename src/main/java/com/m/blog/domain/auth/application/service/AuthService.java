package com.m.blog.domain.auth.application.service;


import com.m.blog.domain.auth.application.domain.Member;
import com.m.blog.domain.auth.application.port.persistence.FindMemberPersistencePort;
import com.m.blog.domain.auth.application.port.servlet.ServletPort;
import com.m.blog.domain.auth.application.usecase.AuthUsecase;
//import com.m.blog.domain.auth.adapter.out.persistence.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUsecase {
    private final FindMemberPersistencePort findMemberPersistencePort;
    private final ServletPort servletPort;

    @Override
    public void logout(){
        servletPort.inValidateSession();
    }

    @Override
    public Member login(Member.LoginInfo info){
        Member member = findMemberPersistencePort.find(info);
        servletPort.setAttribute(member);

        return member;
    }

}
