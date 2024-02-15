package com.m.blog.domain.auth.controller;

import com.m.blog.domain.auth.dto.LoginRequest;
import com.m.blog.domain.auth.dto.LoginResponse;
import com.m.blog.domain.auth.service.AuthService;
import com.m.blog.domain.auth.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequestMapping("/user")
@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @ResponseBody
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletRequest request){
        return authService.login(loginRequest, request);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request){
        authService.logout(request);
    }
}
