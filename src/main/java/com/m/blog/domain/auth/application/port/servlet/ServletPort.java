package com.m.blog.domain.auth.application.port.servlet;

import com.m.blog.domain.auth.application.domain.Member;

public interface ServletPort {
    void setAttribute(Member member);

    void inValidateSession();
}
