package com.m.blog.global.security.session;

import com.m.blog.global.config.variable.SessionConst;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionFilter extends OncePerRequestFilter {
    private final SessionProvider sessionProvider;

    public SessionFilter(SessionProvider sessionProvider){
        this.sessionProvider = sessionProvider;
    }

    private boolean validate(HttpSession httpSession){
        return httpSession != null && httpSession.getAttribute(SessionConst.LOGIN_MEMBER) != null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession();

        if(validate(httpSession)){
            Authentication authentication = sessionProvider.getAuthentication(httpSession);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
