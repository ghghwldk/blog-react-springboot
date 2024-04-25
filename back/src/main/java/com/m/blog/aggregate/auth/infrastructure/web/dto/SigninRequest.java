package com.m.blog.aggregate.auth.infrastructure.web.dto;

import lombok.Data;

@Data
public class SigninRequest {
    private String userId;
    private String password;
}
