package com.m.blog.aggregate.auth.infrastructure.web.controller;

import com.m.blog.aggregate.auth.application.port.in.AuthEndpointPort;
import com.m.blog.aggregate.auth.infrastructure.web.dto.SigninRequest;
import com.m.blog.aggregate.auth.infrastructure.web.dto.SigninResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/auth")
@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthEndpointPort authEndpointPort;
    @ResponseBody
    @PostMapping
    public SigninResponse login(@RequestBody SigninRequest request){
        return authEndpointPort.login(request);
    }

    @ResponseBody
    @DeleteMapping
    public void logout() {
        authEndpointPort.logout();
    }
}
