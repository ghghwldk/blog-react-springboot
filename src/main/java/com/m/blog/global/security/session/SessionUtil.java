package com.m.blog.global.security.session;

import com.m.blog.domain.auth.application.domain.Member;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface SessionUtil {
    void setAttribute(Member member);

    Authentication getAuthentication(HttpServletRequest httpSession);

    boolean validate(HttpServletRequest httpServletRequest);

    void inValidateSession();
}
