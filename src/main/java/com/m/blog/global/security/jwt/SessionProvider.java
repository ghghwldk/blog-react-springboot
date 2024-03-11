package com.m.blog.global.security.jwt;

import com.m.blog.domain.auth.application.domain.Member;
import com.m.blog.global.config.variable.SessionConst;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

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

