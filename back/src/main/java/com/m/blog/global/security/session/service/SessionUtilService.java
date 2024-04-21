package com.m.blog.global.security.session.service;

import com.m.blog.aggregate.auth.application.domain.Member;
import com.m.blog.global.security.session.SessionUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Component
public class SessionUtilService implements SessionUtil {
    private static final String LOGIN_MEMBER = "loginMember";

    @Override
    public void setAttribute(Member member){
        HttpServletRequest httpServletRequest = this.getHttpServletRequest();

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(LOGIN_MEMBER, member);
    }
    @Override
    public boolean validate(HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession();

        return httpSession != null && httpSession.getAttribute(LOGIN_MEMBER) != null;
    }

    private HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes)
                Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
    }

    private HttpSession getSession() {
        return this.getHttpServletRequest()
                .getSession(false);
    }

    @Override
    public void inValidateSession(){
        HttpSession httpSession = this.getSession();

        if(httpSession!=null){
            httpSession.invalidate();
        }
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();

        Member member = (Member) httpSession.getAttribute(LOGIN_MEMBER);

        UserDetails principal = User.builder()
                .username(member.getMemberId().getValue())
                .authorities(member.getRole())
                .password("") // Empty password as we're using token authentication
                .build();

        return new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());
    }
}
