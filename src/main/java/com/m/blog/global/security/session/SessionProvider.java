package com.m.blog.global.security.session;

import com.m.blog.domain.auth.application.domain.Member;
import com.m.blog.global.config.variable.SessionConst;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class SessionProvider {

    public Authentication getAuthentication(HttpSession httpSession) {
        Member member = (Member) httpSession.getAttribute(SessionConst.LOGIN_MEMBER);

        UserDetails principal = User.builder()
                .username(member.getId().getUserId())
                .authorities(member.getRole())
                .password("") // Empty password as we're using token authentication
                .build();

        return new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());
    }
}

