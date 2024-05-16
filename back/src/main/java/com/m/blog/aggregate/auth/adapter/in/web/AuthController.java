package com.m.blog.aggregate.auth.adapter.in.web;

import com.m.blog.aggregate.auth.application.domain.Member;
import com.m.blog.aggregate.auth.application.port.in.AuthUsecase;
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
    private final AuthUsecase authUsecase;

    @ResponseBody
    @PostMapping
    public ResponseEntity<SigninResponse> login(@RequestBody SigninRequest request){
        Member member = authUsecase.login(request.getUserId(), request.getPassword());

        return ResponseEntity.ok(AuthEntrypointMapper.from(member));
    }

    @ResponseBody
    @DeleteMapping
    public ResponseEntity logout() {
        authUsecase.logout();

        return ResponseEntity.ok(null);
    }
}
