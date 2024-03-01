package com.m.blog.domain.auth.infrastructure.web.controller;

import com.m.blog.domain.auth.application.port.entrypoint.api.AuthEndpointPort;
import com.m.blog.domain.auth.infrastructure.web.dto.LoginRequest;
import com.m.blog.domain.auth.infrastructure.web.dto.LoginResponse;
import com.m.blog.domain.auth.application.usecase.AuthUsecase;
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
    private final AuthEndpointPort authEndpointPort;

    @ResponseBody
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletRequest request){
        return authEndpointPort.login(loginRequest, request);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request){
        authEndpointPort.logout(request);
    }
}
