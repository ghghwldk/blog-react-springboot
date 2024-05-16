package com.m.blog.aggregate.auth.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SigninResponse {
    private String role;
}
