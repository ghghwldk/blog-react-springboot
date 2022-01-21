package com.m.blog.security;

import com.m.blog.service.FilterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class CustomAuthenticationFilter {
    FilterService filterService = new FilterService();

    public boolean doFilter(String requestURI, String queryString, String method, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(method.equals("GET")){
//            if (requestURI.equals("/posting/list") || requestURI.equals("/board/show") || requestURI.equals("/posting/view/posting")) {
//                if("4".equals(filterService.getBoardCollectionId(requestURI, method, request))){
//                    return filterService.checkIsMaster(session);
//                }
//            }else{
//                return true;
//            }
        }else{
            List<String> conditions = new LinkedList<String>(Arrays.asList(
                    "/file/upload",
                    "/posting/data/update",
                    "/posting/data/insert/posting"
            ));
            if(conditions.contains(requestURI)){
                return filterService.checkIsMaster(session);
            }
        }
        return true;
    }
}