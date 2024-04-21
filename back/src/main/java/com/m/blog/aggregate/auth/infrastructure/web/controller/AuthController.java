package com.m.blog.aggregate.auth.infrastructure.web.controller;

import com.m.blog.aggregate.auth.application.port.entrypoint.api.AuthEndpointPort;
import com.m.blog.aggregate.auth.infrastructure.web.dto.LoginRequest;
import com.m.blog.aggregate.auth.infrastructure.web.dto.LoginResponse;
import com.m.blog.aggregate.auth.infrastructure.web.dto.LogoutResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Slf4j
@RequestMapping("/auth")
@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthEndpointPort authEndpointPort;
    @ResponseBody
    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest request){
        return authEndpointPort.login(request);
    }

    @ResponseBody
    @DeleteMapping
    public void logout() {
        authEndpointPort.logout();
    }
}
