package com.m.blog.domain.auth.adapter.servlet;

import com.m.blog.common.Adapter;
import com.m.blog.domain.auth.application.domain.Member;
import com.m.blog.domain.auth.application.port.servlet.ServletPort;
import com.m.blog.global.security.session.SessionUtil;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class ServletAdapter implements ServletPort{
    private final SessionUtil sessionUtil;

    @Override
    public void setAttribute(Member member){
        sessionUtil.setAttribute(member);
    }

    @Override
    public void inValidateSession(){
        sessionUtil.inValidateSession();
    }
}
