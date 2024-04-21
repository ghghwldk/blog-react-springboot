package com.m.blog.global.security.session;

import com.m.blog.aggregate.auth.application.domain.Member;
import com.m.blog.global.security.session.vo.SessionData;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface SessionUtil {
    void setAttribute(SessionData sessionData);


    boolean validate();

    void inValidateSession();

    Authentication getAuthentication();
}
