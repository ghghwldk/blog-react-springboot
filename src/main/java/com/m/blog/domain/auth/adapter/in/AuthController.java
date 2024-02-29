package com.m.blog.domain.auth.adapter.in;

import com.m.blog.domain.auth.application.port.in.LoginRequest;
import com.m.blog.domain.auth.application.port.out.LoginResponse;
import com.m.blog.domain.auth.application.port.in.AuthUseCase;
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
    private final AuthUseCase authUseCase;

    @ResponseBody
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletRequest request){
        return authUseCase.login(loginRequest, request);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request){
        authUseCase.logout(request);
    }
}
