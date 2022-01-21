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

            }else{
//                // fileUpload는 인증 성공한 유저만 통과
//                if(requestURI.equals("/file/upload")){
//                    return false;
//                }else{
//                    // get 이외의 요청은 redirect 하지 않는다.
//                    // 요청도 보내지 못하도록 버튼을 막아버릴 것이기 때문!
//                    return false;
//                }
            }
        }
        // 있으면, 인증은 무조건 통과
        return true;
    }
}


