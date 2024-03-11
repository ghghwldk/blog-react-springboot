package com.m.blog.domain.auth.infrastructure.web.controller;

import com.m.blog.domain.auth.application.port.entrypoint.api.AuthEndpointPort;
import com.m.blog.domain.auth.infrastructure.repository.MemberEntity;
import com.m.blog.domain.auth.infrastructure.repository.MemberJpaRepository;
import com.m.blog.domain.auth.infrastructure.web.dto.LoginRequest;
import com.m.blog.domain.auth.infrastructure.web.dto.LoginResponse;
import com.m.blog.domain.auth.application.usecase.AuthUsecase;
import com.m.blog.global.config.variable.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@RequestMapping("/user")
@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthEndpointPort authEndpointPort;
    private final MemberJpaRepository memberJpaRepository;

    @ResponseBody
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request, HttpServletRequest httpServletRequest){
        return authEndpointPort.login(request, httpServletRequest);
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        return authEndpointPort.logout(request);
    }
}
