package com.m.blog.aggregate.auth.adapter.in.web;

import com.m.blog.aggregate.auth.application.port.in.AuthEndpointPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/auth")
@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthEndpointPort authEndpointPort;

    @ResponseBody
    @PostMapping
    public ResponseEntity<SigninResponse> login(@RequestBody SigninRequest request){
        return ResponseEntity.ok(authEndpointPort.login(request));
    }

    @ResponseBody
    @DeleteMapping
    public ResponseEntity logout() {
        authEndpointPort.logout();

        return ResponseEntity.ok(null);
    }
}
