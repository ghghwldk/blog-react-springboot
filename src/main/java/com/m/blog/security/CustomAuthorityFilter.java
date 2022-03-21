package com.m.blog.security;

import com.m.blog.service.FilterService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;

@Slf4j
public class CustomAuthorityFilter {
    FilterService filterService= new FilterService();

    public boolean doFilter(String requestURI, String queryString, String method, HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(!filterService.hasSession(httpSession)) {
            if(method.equals("GET")){
              // get은 모두 통과
            }else if(method.equals("POST")){
               if(requestURI.equals("/file/upload")){
                   return false;
               }
            }
        }
        // 있으면, 인증은 무조건 통과
        return true;
    }
}


