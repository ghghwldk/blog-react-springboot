package com.m.blog.domain.auth.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LogoutResponse {
    private static final String signOutRedirectUri = "redirect:/";
}
