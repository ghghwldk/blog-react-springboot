package com.m.blog.global.security.session.service;

import com.m.blog.aggregate.auth.application.domain.Member;
import com.m.blog.global.security.session.SessionUtil;
import com.m.blog.global.security.session.vo.SessionData;
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
    private static final String SessionData = "SessionData";

    @Override
    public void setAttribute(SessionData sessionData){
        this.getSession()
                .setAttribute(SessionData, sessionData);
    }
    @Override
    public boolean validate(){
        HttpSession httpSession = this.getSession();

        return httpSession != null && httpSession.getAttribute(SessionData) != null;
    }

    private HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes)
                Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
    }

    private HttpSession getSession() {
        return this.getHttpServletRequest()
                .getSession();
    }

    @Override
    public void inValidateSession(){
        HttpSession httpSession = this.getSession();

        if(httpSession!=null){
            httpSession.invalidate();
        }
    }

    @Override
    public Authentication getAuthentication() {
        SessionData sessionData = (SessionData) this.getSession().getAttribute(SessionData);

        UserDetails principal = User.builder()
                .username(sessionData.getMemberId())
                .authorities(sessionData.getRole())
                .password("") // Empty password as we're using token authentication
                .build();

        return new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());
    }
}
