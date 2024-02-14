package com.m.blog.domain.auth.service;

import com.m.blog.global.config.variable.SessionConst;
import com.m.blog.domain.auth.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
@Slf4j
public class FilterService {
    public String getBoardCollectionId(String requestURI, String method, HttpServletRequest request){
        String boardCollectionId=null;
        if(method.equals("GET")){
            if (requestURI.equals("/posting/list") || requestURI.equals("/board/show") || requestURI.equals("/posting/view/posting")) {
                boardCollectionId = request.getParameter("boardCollectionId");
            }
        }else{
            String jsonStr = (String) request.getAttribute("requestBody");
            try {
                JSONObject jsonObject = new JSONObject(jsonStr);
                boardCollectionId = jsonObject.getString("boardCollectionId");

            } catch (JSONException e) {
                log.info(e.getMessage());
            }
        }
        return boardCollectionId;
    }

    public boolean hasSession(HttpSession httpSession){
        if(httpSession == null|| httpSession.getAttribute(SessionConst.LOGIN_MEMBER) == null){
            return false;
        }
        return true;
    }

    public void processRedirectLogic(String method, String requestURI, String queryString, HttpServletResponse response) throws IOException {
        String redirectURL=null;
        if(method.equals("GET")){
            redirectURL= "/user/login?redirectURL=" + requestURI;
            if (queryString != null) {
                redirectURL = redirectURL + "?" + queryString.replace('&', '$');
            }
        }
        response.sendRedirect(redirectURL);
    }

    public boolean checkIsMaster(HttpSession session){
        Member loginnedMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (!loginnedMember.getRole().equals("master")) {
            return false;
        }
        return true;
    }
}