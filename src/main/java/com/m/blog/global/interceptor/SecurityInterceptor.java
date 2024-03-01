package com.m.blog.global.interceptor;

import com.google.gson.Gson;
import com.m.blog.global.security.CustomAuthenticationFilter;
import com.m.blog.global.security.CustomAuthorityFilter;
import com.m.blog.domain.auth.application.service.FilterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Slf4j
@Component
public class SecurityInterceptor implements HandlerInterceptor {
    @Autowired
    FilterService filterService= new FilterService();
    CustomAuthorityFilter customAuthorityFilter= new CustomAuthorityFilter();
    CustomAuthenticationFilter customAuthenticationFilter= new CustomAuthenticationFilter();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();
        String method= request.getMethod();
        HttpSession session = request.getSession();

        if(! customAuthorityFilter.doFilter(requestURI, queryString, method, session, request, response)){
            HashMap<String, String> message= new HashMap<>(){{
                put("message","로그인을 하지 않았기 때문에 해당 과정을 진행할 수 없습니다.");
            }};
            response.getWriter().write(new Gson().toJson(message));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(400);
            return false;
        }
        if(filterService.hasSession(session)){
            if(!customAuthenticationFilter.doFilter(requestURI, queryString, method, session, request, response)){
                return false;
            }
        }
        return true;
    }
}
