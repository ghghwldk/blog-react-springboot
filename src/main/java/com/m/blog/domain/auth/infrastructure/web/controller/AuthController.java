package com.m.blog.domain.auth.infrastructure.web.controller;

import com.m.blog.domain.auth.application.port.entrypoint.api.AuthEndpointPort;
import com.m.blog.domain.auth.infrastructure.web.dto.LoginRequest;
import com.m.blog.domain.auth.infrastructure.web.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequestMapping("/user")
@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthEndpointPort authEndpointPort;
    @ResponseBody
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        return authEndpointPort.login(request);
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        return authEndpointPort.logout(request);
    }
}
