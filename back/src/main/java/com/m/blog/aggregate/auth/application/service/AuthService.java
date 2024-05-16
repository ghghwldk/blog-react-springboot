package com.m.blog.aggregate.auth.application.service;


import com.m.blog.global.customAnnotation.UseCase;
import com.m.blog.aggregate.auth.application.domain.Member;
import com.m.blog.aggregate.auth.application.port.out.FindMemberPersistencePort;
import com.m.blog.aggregate.auth.application.port.in.AuthUsecase;
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
    public Member login(String userId, String password){
        Member found = findMemberPersistencePort.find(userId)
                .orElseThrow(DataNotFoundException::new);

        checkPassword(password, found.getPassword());

        setSessionData(userId, password);

        return found;
    }

    private void setSessionData(String userId, String password){
        SessionData sessionData = SessionData.builder()
                .memberId(userId)
                .role(password)
                .build();

        sessionUtil.setAttribute(sessionData);
    }

    private void checkPassword(String password, String retrievedPassword){
        boolean isMatched = passwordEncoder
                .matches(password, retrievedPassword);

        if(!isMatched) throw new PasswordNotMatchedException();
    }
}
