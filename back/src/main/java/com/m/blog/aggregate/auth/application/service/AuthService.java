package com.m.blog.aggregate.auth.application.service;


import com.m.blog.global.customAnnotation.UseCase;
import com.m.blog.aggregate.auth.application.domain.Member;
import com.m.blog.aggregate.auth.application.port.out.FindMemberPersistencePort;
import com.m.blog.aggregate.auth.application.usecase.AuthUsecase;
//import com.m.blog.domain.auth.adapter.out.persistence.Member;
import com.m.blog.global.exception.DataNotFoundException;
import com.m.blog.global.exception.PasswordNotMatchedException;
import com.m.blog.global.security.session.SessionUtil;
import com.m.blog.global.security.session.vo.SessionData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@UseCase
@RequiredArgsConstructor
public class AuthService implements AuthUsecase {
    private final FindMemberPersistencePort findMemberPersistencePort;
    private final SessionUtil sessionUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void logout(){
        sessionUtil.inValidateSession();
    }

    @Override
    public Member login(Member member){
        Member found = findMemberPersistencePort.find(member)
                .orElseThrow(DataNotFoundException::new);

        boolean isMatched = passwordEncoder
                .matches(member.getPassword(), found.getPassword());

        if(!isMatched) throw new PasswordNotMatchedException();

        sessionUtil.setAttribute(SessionData.builder()
                .memberId(found.getMemberId().getValue())
                .role(found.getRole())
                .build()
        );

        return found;
    }

}
