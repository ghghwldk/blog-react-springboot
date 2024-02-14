package com.m.blog.domain.auth.controller;

import com.m.blog.global.config.variable.SessionConst;
import com.m.blog.domain.auth.entity.Member;
import com.m.blog.domain.auth.repository.MemberJpaRepository;
import com.m.blog.domain.auth.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Slf4j
@RequestMapping("/user")
@Controller
@RequiredArgsConstructor
public class UserController {
    @Autowired
    LoginService loginService;
    @Autowired
    MemberJpaRepository memberJpaRepository;

    @ResponseBody
    @PostMapping("/login")
    public HashMap<String, String> loginV4(@RequestBody HashMap<String, String> requestBody, HttpServletRequest request){
        HashMap<String, String> result= new HashMap<String, String>();

        String id = requestBody.get("id");
        String password = requestBody.get("password");
        Member loginMember= loginService.checkMemberOrNot(id, password);
        if(loginMember== null){
            result.put("message","it is not correct information.");
            return result;
        }
        // if entered information is about the member, then we have to process the login
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        result.put("message","success");
        result.put("role", loginMember.getRole());


        return result;
    }

    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request){
        HttpSession session= request.getSession(false);
        if(session!=null){
            session.invalidate();
        }
        return "redirect:/";
    }
}
