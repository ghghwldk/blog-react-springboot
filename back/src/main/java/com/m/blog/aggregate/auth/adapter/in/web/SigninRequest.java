package com.m.blog.aggregate.auth.adapter.in.web;

import lombok.Data;

@Data
public class SigninRequest {
    private String userId;
    private String password;
}
