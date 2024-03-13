package com.m.blog.global.security.session;

import com.m.blog.domain.auth.application.domain.Member;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
public interface SessionUtil {
    void setAttribute(Member member, HttpServletRequest httpServletRequest);

    Authentication getAuthentication(HttpServletRequest httpSession);

    boolean validate(HttpServletRequest httpServletRequest);
}
