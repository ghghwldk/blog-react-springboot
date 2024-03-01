package com.m.blog.domain.auth.infrastructure.web.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String userId;
    private String password;
}
